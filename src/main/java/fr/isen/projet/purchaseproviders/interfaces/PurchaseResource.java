@Path("/purchases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    @POST
    public Response createPurchase(PurchaseModel purchase) {
        // Implémentation : Ajouter l'achat (appel à la couche Service/Repository)
        return Response.ok("Purchase created successfully").build();
    }

    @GET
    @Path("/{id}")
    public Response readPurchase(@PathParam("id") int id) {
        // Implémentation : Récupérer l'achat par ID
        PurchaseModel purchase = new PurchaseModel();
        return Response.ok(purchase).build();
    }

    @GET
    public Response readAllPurchases() {
        // Implémentation : Récupérer tous les achats
        List<PurchaseModel> purchases = new ArrayList<>();
        return Response.ok(purchases).build();
    }

    @PUT
    public Response updatePurchase(PurchaseModel purchase) {
        // Implémentation : Mettre à jour l'achat
        return Response.ok("Purchase updated successfully").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePurchase(@PathParam("id") int id) {
        // Implémentation : Supprimer l'achat
        return Response.noContent().build();
    }

    @GET
    @Path("/price-evolution/{idProduct}")
    public Response getPriceEvolution(@PathParam("idProduct") String idProduct) {
        List<PurchaseModel> purchases = purchaseRepository.find("idProduct", idProduct).list();
        List<Double> priceEvolution = purchases.stream()
                .map(PurchaseModel::getPrice)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return Response.ok(priceEvolution).build();
    }

}
