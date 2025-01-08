/*package fr.isen.projet.purchaseproviders;

import fr.isen.projet.adressesetcontacts.interfaces.services.AddressService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class purchaseResource {

    @Inject
    private AddressService addressService;

    // Endpoint pour récupérer toutes les adresses
    @GET
    public List<AddressModel> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // Endpoint pour récupérer une adresse par son UUID
    @GET
    @Path("/{uuid}")
    public Response getAddressById(@PathParam("uuid") String uuid) {
        AddressModel address = addressService.getAddressById(uuid);
        if (address == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Adresse non trouvée").build();
        }
        return Response.ok(address).build();
    }

    // Endpoint pour créer une nouvelle adresse
    @POST
    public Response createAddress(AddressModel address) {
        AddressModel createdAddress = addressService.createAddress(address);
        return Response.status(Response.Status.CREATED).entity(createdAddress).build();
    }

    // Endpoint pour mettre à jour une adresse existante
    @PUT
    @Path("/{uuid}")
    public Response updateAddress(@PathParam("uuid") String uuid, AddressModel updatedAddress) {
        AddressModel address = addressService.updateAddress(uuid, updatedAddress);
        if (address == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Adresse non trouvée").build();
        }
        return Response.ok(address).build();
    }

    // Endpoint pour supprimer une adresse par son UUID
    @DELETE
    @Path("/{uuid}")
    public Response deleteAddress(@PathParam("uuid") String uuid) {
        addressService.deleteAddress(uuid);
        return Response.noContent().build();
    }

}*/