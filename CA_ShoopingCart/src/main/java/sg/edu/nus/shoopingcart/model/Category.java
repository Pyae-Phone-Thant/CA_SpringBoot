package sg.edu.nus.shoopingcart.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//Author Pyae Phone Thant
@Entity
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int category_id;
	@Column(length=25,nullable=false)
	@NotBlank(message="Category name must be enter")
	@Size(max=25,message="Category name must be between 25 characters")
	private String category_name;
	private String category_description;
	@ManyToOne
	@JoinColumn(name="created_by")
	private Customer createdBy;
	private LocalDateTime created_date;
	@Column(nullable=false,columnDefinition="bit default 1")
	private Boolean status;
	@OneToMany(mappedBy="category")
	private List<Product> products;
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public Customer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Customer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
