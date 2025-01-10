package fr.isen.projet.purchaseproviders;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/status")
public class StatusResource {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/projet"; // URL de la base
    private static final String DB_USER = "root"; // Nom d'utilisateur
    private static final String DB_PASSWORD = "9867"; // Mot de passe

    // Tables pour lesquelles on compte les éléments
    private static final Set<String> ALLOWED_TABLES = Set.of("provider", "purchase");

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getStatus() throws JsonProcessingException {
        String state = "OK"; // Indicateur de l'état général (OK, KO, ou Dégradé)
        String version = "1.0"; // Version de l'API

        Map<String, Long> tableCounts = new HashMap<>();
        try {
            // Récupérer les comptes des tables
            tableCounts = getTableCounts();
        } catch (Exception e) {
            state = "KO"; // L'état passe à KO si une erreur survient
            e.printStackTrace();
        }

        // Construire la réponse JSON
        Map<String, Object> statusResponse = new HashMap<>();
        statusResponse.put("state", state);
        statusResponse.put("version", version);
        statusResponse.put("tableCounts", tableCounts);

        // Convertir la réponse en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(statusResponse);
    }

    private Map<String, Long> getTableCounts() throws Exception {
        Map<String, Long> tableCounts = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Récupérer la liste des tables dans la base de données
            try (Statement tableStatement = connection.createStatement();
                 ResultSet tables = tableStatement.executeQuery("SHOW TABLES")) {

                while (tables.next()) {
                    String tableName = tables.getString(1);

                    // Vérifier si la table est dans la liste autorisée
                    if (ALLOWED_TABLES.contains(tableName)) {
                        try (Statement countStatement = connection.createStatement();
                             ResultSet countResult = countStatement.executeQuery("SELECT COUNT(*) FROM `" + tableName + "`")) {

                            if (countResult.next()) {
                                tableCounts.put(tableName, countResult.getLong(1));
                            }
                        } catch (Exception e) {
                            System.err.println("Erreur lors du comptage pour la table " + tableName + ": " + e.getMessage());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion ou de l'exécution de la requête : " + e.getMessage());
            throw e;
        }

        return tableCounts;
    }
}
