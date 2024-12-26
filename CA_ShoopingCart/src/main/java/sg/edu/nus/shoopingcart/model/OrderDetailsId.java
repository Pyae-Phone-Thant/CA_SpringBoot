package sg.edu.nus.shoopingcart.model;
//Author: Ong Xin Min
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderDetailsId implements Serializable {
    private Long orderHistoryId; // First part of the composite key
    private Product product;      // Second part of the composite key

    // Default constructor
    public OrderDetailsId() {}

    // Parameterized constructor
    public OrderDetailsId(Long orderHistoryId, Product product) {
        this.orderHistoryId = orderHistoryId;
        this.product=product;
    }

    // Getters and Setters
    public Long getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(Long orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProductId(Product product) {
        this.product=product;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetailsId)) return false;
        OrderDetailsId that = (OrderDetailsId) o;
        return Objects.equals(orderHistoryId, that.orderHistoryId) &&
               Objects.equals(product, that.product);
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(orderHistoryId, product);
    }
}
