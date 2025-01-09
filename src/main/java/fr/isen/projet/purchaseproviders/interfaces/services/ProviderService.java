package fr.isen.projet.purchaseproviders.interfaces.services;

import java.util.List;
import fr.isen.projet.purchaseproviders.interfaces.models.ProviderModel;

public interface ProviderService {
    ProviderModel createProvider(final ProviderModel provider);
    ProviderModel readProvider(final String id);
    List<ProviderModel> readAllProvider();
    ProviderModel updateProvider(final ProviderModel provider);
    void deleteProvider(final String id);
    float getAnnualRevenue(final int startDate);
    List<Float> getMonthlyRevenue(final int satrtDate);
}