package sg.edu.nus.shoopingcart.model;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

//Author: Ong Xin Min
@Entity
@Table(name = "OrderDetails")
@IdClass(OrderDetailsId.class) // Specify the composite key class
public class OrderDetails implements Serializable {
	@Id
	@Column(name = "order_history_id")
	private Long orderHistoryId; // Part of the composite key

	@ManyToOne
	@Id
	@JoinColumn(name="product_id")
	private Product product; // Part of the composite key

	@ManyToOne
	@JoinColumn(name = "order_history_id", insertable = false, updatable=false)
	private OrderHistory orderHistory;

	@Column(name = "quantity")
	private int quantity;
	@Column(name = "sale_price")
	private double salePrice;
	// Getters and Setters
    public Long getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(Long orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

	/*
	 * public int getProductId() { return productId; }
	 * 
	 * public void setProductId(int productId) { this.productId = productId; }
	 */

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
        this.orderHistoryId = orderHistory.getOrderId(); // Set the order history ID from the relationship
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSalePrice() {
    	return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
