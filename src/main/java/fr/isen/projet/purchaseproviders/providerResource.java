package fr.isen.purchaseproviders.resources;

import fr.isen.purchaseproviders.interfaces.models.ProviderModel;
import fr.isen.purchaseproviders.interfaces.models.PurchaseModel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Path("/providers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProviderResource {

    @Inject
    private ProviderService providerService;

    // Endpoint pour récupérer tous les provider
    @GET
    public List<ProviderModel> getAllProviders() {
        return providerService.getAllProviders();
    }

    // Endpoint pour récupérer une provider par son ID
    @GET
    @Path("/{id}")
    public Response readProvider(@PathParam("id") String id) {
        ProviderModel provider = providerService.readProvider(id);
        if (provider == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Provider non trouvée").build();
        }
        return Response.ok(provider).build();
    }

    // Endpoint pour créer un nouveau provider
    @POST
    public Response createProvider(ProviderModel provider) {
        ProviderModel createdProvider = providerService.createProvider(provider);
        return Response.status(Response.Status.CREATED).entity(createdProvider).build();
    }

    /*// Endpoint pour mettre à jour une provider existante
    @PUT
    @Path("/{id}")
    public Response updateProvider(@PathParam("id") String id, ProviderModel updatedProvider) {
        ProviderModel provider = providerService.updateProvider(updatedProvider);
        if (provider == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Adresse non trouvée").build();
        }
        return Response.ok(provider).build();
    }

    @GET
    public Response readAllProviders() {
        // Implémentation : Récupérer tous les fournisseurs
        List<ProviderModel> providers = new ArrayList<>();
        return Response.ok(providers).build();
    }

    @PUT
    public Response updateProvider(ProviderModel provider) {
        // Implémentation : Mettre à jour le fournisseur
        return Response.ok("Provider updated successfully").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProvider(@PathParam("id") int id) {
        // Implémentation : Supprimer le fournisseur
        return Response.noContent().build();
    }*/
}
