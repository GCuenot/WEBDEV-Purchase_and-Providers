package fr.isen.projet.purchaseproviders.interfaces.models;

public class ProviderModel {
    private String id;
    private String name;
    private String service;
    private String siren;
    private String status;
    private String id_contact;
    private String registration_date;
    private String region;
    private String legal_informations;
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

    // Getter et Setter pour siren
    public String getSIREN() {
        return siren;
    }

    public void setSIREN(String siren) {
        this.siren = siren;
    }

    // Getter et Setter pour status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter et Setter pour id_contact
    public String getIdContact() {
        return id_contact;
    }

    public void setIdContact(String id_contact) {
        this.id_contact = id_contact;
    }

    // Getter et Setter pour registration_date
    public String getRegistrationDate() {
        return registration_date;
    }

    public void setRegistrationDate(String registration_date) {
        this.registration_date = registration_date;
    }

    // Getter et Setter pour region
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // Getter et Setter pour legal_informations
    public String getLegalInformations() {
        return legal_informations;
    }

    public void setLegalInformations(String legal_informations) {
        this.legal_informations = legal_informations;
    }

    // Getter et Setter pour category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
