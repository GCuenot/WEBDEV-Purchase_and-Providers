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
    public String createProvider(ProviderModel provider) {
        return "";
    }

    @Override
    public ProviderModel readProvider(String id) {
        return null;
    }

    @Override
    public List<ProviderModel> getAllProviders() {
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
                provider.setRegistrationDate(rs.getString("registration"));
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
    public void deleteProvider(int id) {

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


