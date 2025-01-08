package fr.isen.projet.purchaseproviders.interfaces.services;

import java.util.List;
import fr.isen.projet.purchaseproviders.interfaces.models.PurchaseModel;

//begin of modifiable zone(Javadoc).......C/f2be4275-01ff-4a68-9c64-2c1bc28af114

//end of modifiable zone(Javadoc).........E/f2be4275-01ff-4a68-9c64-2c1bc28af114
public interface PurchaseService {
//begin of modifiable zone(Javadoc).......C/027a1e00-ef75-415c-aa23-ca18bf7cef26

//end of modifiable zone(Javadoc).........E/027a1e00-ef75-415c-aa23-ca18bf7cef26
    PurchaseModel createPurchase(final PurchaseModel provider);

//begin of modifiable zone(Javadoc).......C/92b6adf5-8926-40da-a983-011ed0fe123a

//end of modifiable zone(Javadoc).........E/92b6adf5-8926-40da-a983-011ed0fe123a
    PurchaseModel readPurchase(final String id);

//begin of modifiable zone(Javadoc).......C/96b354fc-6445-4b60-94ea-794324313200

//end of modifiable zone(Javadoc).........E/96b354fc-6445-4b60-94ea-794324313200
    List<PurchaseModel> readAllPurchase();

//begin of modifiable zone(Javadoc).......C/5a95f963-775b-409c-9de4-9111476bdda8

//end of modifiable zone(Javadoc).........E/5a95f963-775b-409c-9de4-9111476bdda8
    PurchaseModel updatePurchase(final PurchaseModel provider);

//begin of modifiable zone(Javadoc).......C/6c49b0b2-1a4e-4127-8c67-5e9e2e39a8f9

//end of modifiable zone(Javadoc).........E/6c49b0b2-1a4e-4127-8c67-5e9e2e39a8f9
    void deletePurchase(final int id);

//begin of modifiable zone(Javadoc).......C/2da40f49-99a8-42c2-b07d-146b64442d34

//end of modifiable zone(Javadoc).........E/2da40f49-99a8-42c2-b07d-146b64442d34
    List<Float> getPriceEvolution(final String IDProduct);

}
