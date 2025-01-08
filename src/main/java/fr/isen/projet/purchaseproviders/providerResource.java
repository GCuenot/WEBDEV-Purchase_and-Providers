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

    @POST
    public Response createProvider(ProviderModel provider) {
        // Implémentation : Ajouter le fournisseur (appel à la couche Service/Repository)
        return Response.ok("Provider created successfully").build();
    }

    @GET
    @Path("/{id}")
    public Response readProvider(@PathParam("id") int id) {
        // Implémentation : Récupérer le fournisseur par ID
        ProviderModel provider = new ProviderModel();
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
    }

    @GET
    @Path("/annual-revenue/{year}")
    public Response getAnnualRevenue(@PathParam("year") int year) {
        List<PurchaseModel> purchases = purchaseRepository.listAll();
        double annualRevenue = purchases.stream()
                .filter(p -> LocalDate.parse(p.getBuyDate()).getYear() == year)
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
        return Response.ok(annualRevenue).build();
    }

    @GET
    @Path("/monthly-revenue/{year}")
    public Response getMonthlyRevenue(@PathParam("year") int year) {
        List<PurchaseModel> purchases = purchaseRepository.listAll();
        double[] monthlyRevenue = new double[12];

        purchases.stream()
                .filter(p -> LocalDate.parse(p.getBuyDate()).getYear() == year)
                .forEach(p -> {
                    int month = LocalDate.parse(p.getBuyDate()).getMonthValue();
                    monthlyRevenue[month - 1] += p.getPrice() * p.getQuantity();
                });

        return Response.ok(monthlyRevenue).build();
    }
}