package fr.isen.projet.purchaseproviders.interfaces.models;

import java.util.Date;
import fr.isen.projet.purchaseproviders.interfaces.models.enums.PURCHASE_STATE;

public class PurchaseModel {
    private String id;
    private Date buyDate;
    private float price;
    private int quantity;
    private PURCHASE_STATE state;
    private String idProduct;

    // Getter et Setter pour id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter et Setter pour buyDate
    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    // Getter et Setter pour price
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // Getter et Setter pour quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter et Setter pour state
    public PURCHASE_STATE getState() {
        return state;
    }

    public void setState(PURCHASE_STATE state) {
        this.state = state;
    }

    // Getter et Setter pour idProduct
    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
