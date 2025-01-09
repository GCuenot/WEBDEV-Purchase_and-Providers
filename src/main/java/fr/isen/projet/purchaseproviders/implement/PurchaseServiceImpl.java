package fr.isen.projet.purchaseproviders.implement;

import fr.isen.projet.purchaseproviders.interfaces.models.PurchaseModel;
import fr.isen.projet.purchaseproviders.interfaces.models.enums.PURCHASE_STATE;
import fr.isen.projet.purchaseproviders.interfaces.services.PurchaseService;
import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PurchaseServiceImpl implements PurchaseService {

    @Inject
    AgroalDataSource dataSource;

    @Override
    public PurchaseModel createPurchase(PurchaseModel purchase) {
        // Vérification que l'idProduct existe dans la table product
        String checkProductSql = "SELECT COUNT(*) FROM product WHERE id_product = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkProductSql)) {

            checkStmt.setString(1, purchase.getIdProduct());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    // Erreur si le produit n'existe pas
                    throw new ProductNotFoundException("Produit avec idProduct " + purchase.getIdProduct() + " non trouvé.");
                }
            }
        } catch (SQLException e) {
            // Si c'est une erreur de connexion ou d'exécution de la requête
            throw new RuntimeException("Erreur lors de la vérification de l'existence du produit", e);
        } catch (ProductNotFoundException e) {
            // Gestion spécifique de l'exception si le produit n'existe pas
            throw e;
        }

        // Si l'idProduct est valide, on peut insérer la nouvelle purchase
        String sql = "INSERT INTO purchase (id, buyDate, price, quantity, state, idProduct) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Conversion de java.util.Date en java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(purchase.getBuyDate().getTime());

            stmt.setString(1, purchase.getId());
            stmt.setDate(2, sqlDate);
            stmt.setFloat(3, purchase.getPrice());
            stmt.setInt(4, purchase.getQuantity());
            stmt.setString(5, purchase.getState().toString());
            stmt.setString(6, purchase.getIdProduct());

            stmt.executeUpdate();
            return purchase;
        } catch (SQLException e) {
            // Gestion des erreurs lors de l'insertion
            throw new RuntimeException("Erreur lors de la création de l'achat", e);
        }
    }

    // Exception personnalisée pour indiquer que le produit n'a pas été trouvé
    public class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }


    @Override
    public PurchaseModel readPurchase(String id) {
        String sql = "SELECT id, buyDate, price, quantity, state, idProduct FROM purchase WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PurchaseModel purchase = new PurchaseModel();
                    purchase.setId(rs.getString("id"));
                    purchase.setBuyDate(rs.getDate("buyDate"));
                    purchase.setPrice(rs.getFloat("price"));
                    purchase.setQuantity(rs.getInt("quantity"));
                    purchase.setState(PURCHASE_STATE.valueOf(rs.getString("state")));
                    purchase.setIdProduct(rs.getString("idProduct"));
                    return purchase;
                } else {
                    throw new RuntimeException("Purchase not found for ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching purchase by ID", e);
        }
    }

    @Override
    public List<PurchaseModel> readAllPurchase() {
        List<PurchaseModel> purchases = new ArrayList<>();
        String sql = "SELECT id, buyDate, price, quantity, state, idProduct FROM purchase";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PurchaseModel purchase = new PurchaseModel();
                purchase.setId(rs.getString("id"));
                purchase.setBuyDate(rs.getDate("buyDate"));
                purchase.setPrice(rs.getFloat("price"));
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setState(PURCHASE_STATE.valueOf(rs.getString("state")));
                purchase.setIdProduct(rs.getString("idProduct"));
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all purchases", e);
        }
        return purchases;
    }

    @Override
    public PurchaseModel updatePurchase(PurchaseModel purchase) {
        String sql = "UPDATE purchase SET buyDate = ?, price = ?, quantity = ?, state = ?, idProduct = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Conversion de java.util.Date en java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(purchase.getBuyDate().getTime());

            stmt.setDate(1, sqlDate);
            stmt.setFloat(2, purchase.getPrice());
            stmt.setInt(3, purchase.getQuantity());
            stmt.setString(4, purchase.getState().toString());
            stmt.setString(5, purchase.getIdProduct());
            stmt.setString(6, purchase.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No purchase found with ID: " + purchase.getId());
            }
            return purchase;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating purchase", e);
        }
    }

    @Override
    public void deletePurchase(String id) {
        String sql = "DELETE FROM purchase WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("No purchase found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting purchase", e);
        }
    }

    @Override
    public List<Float> getPriceEvolution(String idProduct) {
        String sql = "SELECT price FROM purchase WHERE idProduct = ? ORDER BY buyDate DESC";
        List<Float> prices = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idProduct);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prices.add(rs.getFloat("price"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching price evolution for product: " + idProduct, e);
        }
        return prices;
    }
}
