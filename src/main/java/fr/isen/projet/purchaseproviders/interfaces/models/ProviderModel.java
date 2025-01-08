package fr.isen.projet.purchaseproviders.interfaces.models;

public class ProviderModel {
    private String id;
    private String name;
    private String service;
    private String SIREN;
    private String status;
    private String idContact;
    private String registrationDate;
    private String region;
    private String legalInformations;
    private String category;

    // Getter et Setter pour id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter et Setter pour name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter et Setter pour service
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    // Getter et Setter pour SIREN
    public String getSIREN() {
        return SIREN;
    }

    public void setSIREN(String SIREN) {
        this.SIREN = SIREN;
    }

    // Getter et Setter pour status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter et Setter pour idContact
    public String getIdContact() {
        return idContact;
    }

    public void setIdContact(String idContact) {
        this.idContact = idContact;
    }

    // Getter et Setter pour registrationDate
    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    // Getter et Setter pour region
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // Getter et Setter pour legalInformations
    public String getLegalInformations() {
        return legalInformations;
    }

    public void setLegalInformations(String legalInformations) {
        this.legalInformations = legalInformations;
    }

    // Getter et Setter pour category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
