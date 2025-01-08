package fr.isen.projet.purchaseproviders.interfaces.services;

import java.util.List;
import fr.isen.projet.purchaseproviders.interfaces.models.ProviderModel;

//begin of modifiable zone(Javadoc).......C/8c6a112c-031f-466c-894e-c18706d5288b

//end of modifiable zone(Javadoc).........E/8c6a112c-031f-466c-894e-c18706d5288b
public interface ProviderService {
//begin of modifiable zone(Javadoc).......C/b4fe758d-d0c9-4557-bec9-8da9676811c4

//end of modifiable zone(Javadoc).........E/b4fe758d-d0c9-4557-bec9-8da9676811c4
    String createProvider(final ProviderModel provider);

//begin of modifiable zone(Javadoc).......C/f13708f6-05a9-46df-b7c9-8c7cbf5c938b

//end of modifiable zone(Javadoc).........E/f13708f6-05a9-46df-b7c9-8c7cbf5c938b
    ProviderModel readProvider(final String id);

//begin of modifiable zone(Javadoc).......C/a4dd0405-262c-4330-8217-8f4ee061ab44

//end of modifiable zone(Javadoc).........E/a4dd0405-262c-4330-8217-8f4ee061ab44
    List<ProviderModel> getAllProviders();

//begin of modifiable zone(Javadoc).......C/8c3129db-5f70-4f01-87c9-01b1200216ee

//end of modifiable zone(Javadoc).........E/8c3129db-5f70-4f01-87c9-01b1200216ee
    ProviderModel updateProvider(final ProviderModel provider);

//begin of modifiable zone(Javadoc).......C/06b1fe26-fff9-4b02-b42a-823569bb248e

//end of modifiable zone(Javadoc).........E/06b1fe26-fff9-4b02-b42a-823569bb248e
    void deleteProvider(final String id);

//begin of modifiable zone(Javadoc).......C/d76dac1a-7b20-4680-a7b5-87d448403465

//end of modifiable zone(Javadoc).........E/d76dac1a-7b20-4680-a7b5-87d448403465
    float getAnnualRevenue(final int startDate);

//begin of modifiable zone(Javadoc).......C/d42d1743-93b5-4f5c-a34e-02eddeb2877e

//end of modifiable zone(Javadoc).........E/d42d1743-93b5-4f5c-a34e-02eddeb2877e
    List<Float> getMonthlyRevenue(final int satrtDate);

}
