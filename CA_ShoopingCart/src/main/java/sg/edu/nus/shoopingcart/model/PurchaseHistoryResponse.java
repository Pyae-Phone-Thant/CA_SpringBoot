package sg.edu.nus.shoopingcart.model;

import java.util.List;

//Author: Siow Xiang Ying
public class PurchaseHistoryResponse {
    private String orderId;
    private String datePlaced;
    private String total;
    private String deliveredTo;
    private String deliveryAddress;
    private String paymentMethod;
    private List<PurchaseItemResponse> items;

    public PurchaseHistoryResponse(String orderId, String datePlaced, String total, String deliveredTo, String deliveryAddress, String paymentMethod, List<PurchaseItemResponse> items) {
        this.orderId = orderId;
        this.datePlaced = datePlaced;
        this.total = total;
        this.deliveredTo = deliveredTo;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.items = items;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDeliveredTo() {
        return deliveredTo;
    }

    public void setDeliveredTo(String deliveredTo) {
        this.deliveredTo = deliveredTo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<PurchaseItemResponse> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemResponse> items) {
        this.items = items;
    }
}