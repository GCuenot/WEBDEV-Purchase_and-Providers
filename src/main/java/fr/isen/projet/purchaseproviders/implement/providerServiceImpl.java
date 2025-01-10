package fr.isen.projet.purchaseproviders.implement;

import fr.isen.projet.purchaseproviders.interfaces.models.ProviderModel;
import fr.isen.projet.purchaseproviders.interfaces.services.ProviderService;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped // Ceci rend votre classe injectable en tant que bean CDI
public class providerServiceImpl implements ProviderService{

    @Inject
    AgroalDataSource dataSource;


    @Override
    public ProviderModel createProvider(ProviderModel provider) {
        // Vérification si l'id_contact existe dans contact_model
        String checkContactSql = "SELECT COUNT(*) FROM contact_model WHERE uuid = ?";
        String insertSql = "INSERT INTO provider (id, name, service, siren, status, id_contact, registration_date, region, legal_informations, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {
            // Vérifier si l'id_contact est valide
            try (PreparedStatement checkStmt = conn.prepareStatement(checkContactSql)) {
                checkStmt.setString(1, provider.getIdContact());
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        throw new RuntimeException("Error: id_contact " + provider.getIdContact() + " does not exist in contact_model.");
                    }
                }
            }

            // Insérer le provider si l'id_contact est valide
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setString(1, provider.getId());
                stmt.setString(2, provider.getName());
                stmt.setString(3, provider.getService());
                stmt.setString(4, provider.getSIREN());
                stmt.setString(5, provider.getStatus());
                stmt.setString(6, provider.getIdContact());
                stmt.setString(7, provider.getRegistrationDate());
                stmt.setString(8, provider.getRegion());
                stmt.setString(9, provider.getLegalInformations());
                stmt.setString(10, provider.getCategory());
                stmt.executeUpdate();
            }

            return provider;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating provider: " + e.getMessage(), e);
        }
    }

    @Override
    public ProviderModel readProvider(String id) {
        String sql = "SELECT * FROM provider WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ProviderModel provider = new ProviderModel();
                    provider.setId(rs.getString("id"));
                    provider.setName(rs.getString("name"));
                    provider.setService(rs.getString("service"));
                    provider.setSIREN(rs.getString("siren"));
                    provider.setStatus(rs.getString("status"));
                    provider.setIdContact(rs.getString("id_contact"));
                    provider.setRegistrationDate(rs.getString("registration_date"));
                    provider.setRegion(rs.getString("region"));
                    provider.setLegalInformations(rs.getString("legal_informations"));
                    provider.setCategory(rs.getString("category"));
                    return provider;
                } else {
                    throw new RuntimeException("Provider not found for ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching provider by ID", e);
        }
    }

    @Override
    public List<ProviderModel> readAllProvider() {
        List<ProviderModel> providers = new ArrayList<>();
        String sql = "SELECT * FROM provider";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProviderModel provider = new ProviderModel();
                provider.setId(rs.getString("id"));
                provider.setName(rs.getString("name"));
                provider.setService(rs.getString("service"));
                provider.setSIREN(rs.getString("siren"));
                provider.setStatus(rs.getString("status"));
                provider.setIdContact(rs.getString("id_contact"));
                provider.setRegistrationDate(rs.getString("registration_date"));
                provider.setRegion(rs.getString("region"));
                provider.setLegalInformations(rs.getString("legal_informations"));
                provider.setCategory(rs.getString("category"));
                providers.add(provider);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all providers", e);
        }
        return providers;
    }

    @Override
    public ProviderModel updateProvider(ProviderModel provider) {

        String sql = "UPDATE provider SET name = ?, service = ?, siren = ?, status = ?, id_contact = ?, registration_date = ?, region = ?, legal_informations = ?, category = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, provider.getName());
            stmt.setString(2, provider.getService());
            stmt.setString(3, provider.getSIREN());
            stmt.setString(4, provider.getStatus());
            stmt.setString(5, provider.getIdContact());
            stmt.setString(6, provider.getRegistrationDate());
            stmt.setString(7, provider.getRegion());
            stmt.setString(8, provider.getLegalInformations());
            stmt.setString(9, provider.getCategory());
            stmt.setString(10, provider.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No provider found with ID: " + provider.getId());
            }
            return provider;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating provider", e);
        }
    }

    @Override
    public void deleteProvider(String id) {
        String sql = "DELETE FROM provider WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No provider found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting provider", e);
        }
    }

    @Override
    public float getAnnualRevenue(int startDate) {
        // Requête SQL pour récupérer le revenu total annuel à partir de la date de début
        String sql = "SELECT SUM(o.total_amount) AS annual_revenue " +
                "FROM `order` o " +
                "JOIN `product` pr ON o.uuid_item = pr.id_product " +
                "WHERE YEAR(o.date_created) = ?";

        // Variable pour stocker le revenu annuel total
        float annualRevenue = 0;

        // Connexion à la base de données et exécution de la requête
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_donnees", "votre_utilisateur", "votre_mot_de_passe");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Définir le paramètre de l'année (startDate) dans la requête SQL
            stmt.setInt(1, startDate);

            // Exécution de la requête
            try (ResultSet rs = stmt.executeQuery()) {
                // Si des résultats sont trouvés, on récupère la somme
                if (rs.next()) {
                    annualRevenue = rs.getFloat("annual_revenue");  // Récupération du revenu annuel total
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Gestion des exceptions SQL
        }

        return annualRevenue;  // Retourner le revenu annuel total
    }

    @Override
    public List<Float> getMonthlyRevenue(int startDate) {
        /*// Requête SQL pour récupérer le revenu total mensuel à partir de l'année et du mois spécifiés
        String sql = "SELECT SUM(o.total_amount) AS monthly_revenue " +
                "FROM `order` o " +
                "JOIN `product` pr ON o.uuid_item = pr.id_product " +
                "WHERE YEAR(o.date_created) = ? AND MONTH(o.date_created) = ?";

        // Liste pour stocker les revenus mensuels
        List<Float> monthlyRevenue = new ArrayList<>();

        // Connexion à la base de données et exécution de la requête
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votre_base_de_donnees", "votre_utilisateur", "votre_mot_de_passe");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Définir les paramètres de l'année et du mois dans la requête SQL
            stmt.setInt(1, year);  // On définit l'année
            stmt.setInt(2, month); // On définit le mois

            // Exécution de la requête
            try (ResultSet rs = stmt.executeQuery()) {
                // Si des résultats sont trouvés, on récupère la somme
                if (rs.next()) {
                    float revenue = rs.getFloat("monthly_revenue");  // Récupération du revenu mensuel
                    monthlyRevenue.add(revenue);  // Ajouter à la liste
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Gestion des exceptions SQL
        }*/

        return List.of();  // Retourner la liste des revenus mensuels
    }
}


