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
                    throw new ProductNotFoundException("Produit avec idProduct " + purchase.getIdProduct() + " non trouvé.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence du produit", e);
        }

        // Vérification que l'idProvider existe dans la table provider
        String checkProviderSql = "SELECT COUNT(*) FROM provider WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkProviderSql)) {
            checkStmt.setString(1, purchase.getIdProvider());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new ProviderNotFoundException("Fournisseur avec idProvider " + purchase.getIdProvider() + " non trouvé.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence du fournisseur", e);
        }

        // Vérification que l'idOrder existe dans la table orders
        String checkOrderSql = "SELECT COUNT(*) FROM orders WHERE uuid_order = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkOrderSql)) {
            checkStmt.setString(1, purchase.getIdOrder());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) == 0) {
                    throw new OrderNotFoundException("Ordre avec idOrder " + purchase.getIdOrder() + " non trouvé.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence de l'ordre", e);
        }

        // Insertion de la nouvelle purchase
        String sql = "INSERT INTO purchase (id, buyDate, price, quantity, state, idProduct, idOrder, idProvider) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            java.sql.Date sqlDate = new java.sql.Date(purchase.getBuyDate().getTime());

            stmt.setString(1, purchase.getId());
            stmt.setDate(2, sqlDate);
            stmt.setFloat(3, purchase.getPrice());
            stmt.setInt(4, purchase.getQuantity());
            stmt.setString(5, purchase.getState().toString());
            stmt.setString(6, purchase.getIdProduct());
            stmt.setString(7, purchase.getIdOrder());
            stmt.setString(8, purchase.getIdProvider());

            stmt.executeUpdate();
            return purchase;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création de l'achat", e);
        }
    }

    @Override
    public PurchaseModel readPurchase(String id) {
        String sql = "SELECT id, buyDate, price, quantity, state, idProduct, idOrder, idProvider FROM purchase WHERE id = ?";
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
                    purchase.setIdOrder(rs.getString("idOrder"));
                    purchase.setIdProvider(rs.getString("idProvider"));

                    return purchase;
                } else {
                    throw new RuntimeException("Purchase not found for ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de l'achat par ID", e);
        }
    }

    @Override
    public List<PurchaseModel> readAllPurchase() {
        List<PurchaseModel> purchases = new ArrayList<>();
        String sql = "SELECT id, buyDate, price, quantity, state, idProduct, idOrder, idProvider FROM purchase";
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
                purchase.setIdOrder(rs.getString("idOrder"));
                purchase.setIdProvider(rs.getString("idProvider"));
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération de tous les achats", e);
        }
        return purchases;
    }

    @Override
    public PurchaseModel updatePurchase(PurchaseModel purchase) {
        String sql = "UPDATE purchase SET buyDate = ?, price = ?, quantity = ?, state = ?, idProduct = ?, idOrder = ?, idProvider = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            java.sql.Date sqlDate = new java.sql.Date(purchase.getBuyDate().getTime());

            stmt.setDate(1, sqlDate);
            stmt.setFloat(2, purchase.getPrice());
            stmt.setInt(3, purchase.getQuantity());
            stmt.setString(4, purchase.getState().toString());
            stmt.setString(5, purchase.getIdProduct());
            stmt.setString(6, purchase.getIdOrder());
            stmt.setString(7, purchase.getIdProvider());
            stmt.setString(8, purchase.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Aucun achat trouvé avec l'ID : " + purchase.getId());
            }
            return purchase;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour de l'achat", e);
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
                throw new RuntimeException("Aucun achat trouvé avec l'ID : " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression de l'achat", e);
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
            throw new RuntimeException("Erreur lors de la récupération de l'évolution des prix pour le produit : " + idProduct, e);
        }
        return prices;
    }

    public class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public class ProviderNotFoundException extends RuntimeException {
        public ProviderNotFoundException(String message) {
            super(message);
        }
    }

    public class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }
}
