package sg.edu.nus.shoopingcart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Product;

public interface ProductRepository extends JpaRepository <Product,Integer> {
	@Query("select p from Product as p where p.product_brand=:product_brand")
	List<Product> findByProductBrand(@Param("product_brand")String product_brand);  // Find products by brand
	
	@Query("select p from Product as p where p.product_name like Concat('%',:product_name,'%')")
    List<Product> findByProductNameContainingIgnoreCase(@Param("product_name") String product_name);  // Search products by name
	
	@Query("Select distinct(p.product_brand) from Product as p")
	List<String> findDistinctProductBrand();
	
	@Query("Select distinct(p.product_category) from Product as p")
	List<String> findDistinctProductCategory();
	
	@Query("SELECT p FROM Product p WHERE p.product_brand = :brand")
    Page<Product> findAllProductsByBrand(@Param("brand") String brand, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.product_category = :category")
    Page<Product> findAllProductsByCategory(@Param("category") String category, Pageable pageable);
	
	@Query("select p from Product p where p.product_id=:id")
	Product findProductById(@Param("id")int id);
	
	@Query("select p.image_url from Product p where p.product_id=:id")
	String getImageURLByProductId(@Param("id")int id);
}
