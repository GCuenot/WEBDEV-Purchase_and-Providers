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
        String sql = "INSERT INTO provider (id, name, service, siren, status, id_contact, registration_date, region, legal_informations, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            return provider;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating provider", e);
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
        return null;
    }

    @Override
    public void deleteProvider(String id) {

    }

    @Override
    public float getAnnualRevenue(int startDate) {
        return 0;
    }

    @Override
    public List<Float> getMonthlyRevenue(int satrtDate) {
        return List.of();
    }
}


