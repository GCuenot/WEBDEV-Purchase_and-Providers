package fr.isen.projet.purchaseproviders.interfaces.services;

import java.util.List;
import fr.isen.projet.purchaseproviders.interfaces.models.PurchaseModel;

public interface PurchaseService {
    PurchaseModel createPurchase(final PurchaseModel purchase);
    PurchaseModel readPurchase(final String id);
    List<PurchaseModel> readAllPurchase();
    PurchaseModel updatePurchase(final PurchaseModel purchase);
    void deletePurchase(final String id);
    List<Float> getPriceEvolution(final String IdProduct);
}
