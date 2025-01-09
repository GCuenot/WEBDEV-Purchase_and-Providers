package fr.isen.projet.purchaseproviders;

import fr.isen.projet.purchaseproviders.interfaces.services.PurchaseService;
import fr.isen.projet.purchaseproviders.interfaces.models.PurchaseModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    @Inject
    private PurchaseService purchaseService;

    @GET
    public List<PurchaseModel> readAllPurchase() {
        return purchaseService.readAllPurchase();
    }

    @GET
    @Path("/{id}")
    public Response readPurchase(@PathParam("id") String id) {
        PurchaseModel purchase = purchaseService.readPurchase(id);
        if (purchase == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Purchase non trouvé").build();
        }
        return Response.ok(purchase).build();
    }

    @POST
    public Response createPurchase(PurchaseModel purchase) {
        PurchaseModel createdPurchase = purchaseService.createPurchase(purchase);
        return Response.status(Response.Status.CREATED).entity(createdPurchase).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePurchase(@PathParam("id") String id, PurchaseModel updatedPurchase) {
        updatedPurchase.setId(id);
        PurchaseModel purchase = purchaseService.updatePurchase(updatedPurchase);
        if (purchase == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Purchase non trouvé").build();
        }
        return Response.ok(purchase).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePurchase(@PathParam("id") String id) {
        purchaseService.deletePurchase(id);
        return Response.noContent().build();
    }

    // Nouveau endpoint pour Price Evolution
    @GET
    @Path("/price-evolution/{idProduct}")
    public Response getPriceEvolution(@PathParam("idProduct") String idProduct) {
        List<Float> priceEvolution = purchaseService.getPriceEvolution(idProduct);
        if (priceEvolution.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Aucun prix trouvé pour le produit ID : " + idProduct)
                    .build();
        }
        return Response.ok(priceEvolution).build();
    }
}
