package fr.isen.projet.purchaseproviders;

import fr.isen.projet.purchaseproviders.interfaces.services.ProviderService;
import fr.isen.projet.purchaseproviders.interfaces.models.ProviderModel;
import fr.isen.projet.purchaseproviders.implement.providerServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/provider")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class providerResource {

    @Inject
    private ProviderService providerService;

    // Endpoint pour récupérer tous les provider
    @GET
    public List<ProviderModel> readAllProvider() {
        return providerService.readAllProvider();
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProvider(ProviderModel provider) {
        try {
            providerService.createProvider(provider);
            return Response.status(Response.Status.CREATED)
                    .entity("Le provider a bien été ajouté avec succès").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de l'ajout du provider : " + e.getMessage()).build();
        }
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

    // Endpoint pour supprimer une provider par son UUID
    @DELETE
    @Path("/{id}")
    public Response deleteProvider(@PathParam("id") String id) {
        providerService.deleteProvider(id);
        return Response.noContent().build();
    }*/
}
