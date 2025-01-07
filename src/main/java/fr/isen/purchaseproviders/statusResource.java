package fr.isen.purchaseproviders;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/status")
public class statusResource {
    @GET
    public String getStatus() throws JsonProcessingException {
        String state = "OK"; // OK ou KO ou Dégradé
        // L'état dégradé veut dire que votre code interne fonctionne mais que vous attendez un code dont vous êtes dépendants, donc d'un autre groupe
        String version = "1.0";
         int count = getProvidersCount(); // Récupérer le nombre d'éléments dans la BDD

        Map<String, Object> statusResponse = new HashMap<>();
        statusResponse.put("state", state);
        statusResponse.put("count", count);
        statusResponse.put("version", version);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(statusResponse);
    }

    private int getProvidersCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM providers"; // Requête SQL pour compter les enregistrements

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}


