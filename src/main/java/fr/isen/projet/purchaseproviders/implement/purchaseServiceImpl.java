/*package fr.isen.projet.purchaseproviders.implement;


import fr.isen.projet.adressesetcontacts.interfaces.models.AddressModel;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped // Ceci rend votre classe injectable en tant que bean CDI

public class purchaseServiceImpl {

    @Inject
    AgroalDataSource dataSource;

    @Override
    public AddressModel createAddress(AddressModel address) {
        String sql = "INSERT INTO address_model (uuid, street, city, country) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address.getUuid());
            stmt.setString(2, address.getStreetName());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getCountry());
            stmt.executeUpdate();
            return address;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating address", e);
        }
    }

    @Override
    public AddressModel getAddressById(String uuid) {
        String sql = "SELECT uuid, street, city, country FROM address_model WHERE uuid = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, uuid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AddressModel address = new AddressModel();
                    address.setUuid(rs.getString("uuid"));
                    address.setStreetName(rs.getString("street"));
                    address.setCity(rs.getString("city"));
                    address.setCountry(rs.getString("country"));
                    return address;
                } else {
                    throw new RuntimeException("Address not found for UUID: " + uuid);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching address by UUID", e);
        }
    }

    @Override
    public List<AddressModel> getAllAddresses() {
        List<AddressModel> addresses = new ArrayList<>();
        String sql = "SELECT uuid, street, city, country FROM address_model";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AddressModel address = new AddressModel();
                address.setUuid(rs.getString("uuid"));
                address.setStreetName(rs.getString("street"));
                address.setCity(rs.getString("city"));
                address.setCountry(rs.getString("country"));
                addresses.add(address);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all addresses", e);
        }
        return addresses;
    }

    @Override
    public AddressModel updateAddress(String uuid, AddressModel address) {
        String sql = "UPDATE address_model SET street = ?, city = ?, country = ? WHERE uuid = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address.getStreetName());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getCountry());
            stmt.setString(4, uuid);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No address found with UUID: " + uuid);
            }
            return address;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating address", e);
        }
    }

    @Override
    public void deleteAddress(String uuid) {
        String sql = "DELETE FROM address_model WHERE uuid = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, uuid);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No address found with UUID: " + uuid);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting address", e);
        }
    }
}*/
