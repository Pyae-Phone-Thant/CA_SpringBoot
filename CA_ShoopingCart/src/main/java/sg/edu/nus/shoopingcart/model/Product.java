package sg.edu.nus.shoopingcart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int product_id;
	private String product_name;
	private String product_description;
	private double purchase_price;
	private double sale_price;
	private String product_brand;
	private int quantity;
	private String image_url;
	private String product_category;
	private String product_feature;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_brand() {
		return product_brand;
	}
	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}
	public double getSale_price() {
		return sale_price;
	}
	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}
	public double getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_feature() {
		return product_feature;
	}
	public void setProduct_feature(String product_feature) {
		this.product_feature = product_feature;
	}
	
	
}
