package sg.edu.nus.shoopingcart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Product;
//Author Pyae Phone Thant
public interface ProductRepository extends JpaRepository <Product,Integer> {
	@Query("select count(*) from Product as p where p.status=true")
	int findTotalCount();
	@Query("select p from Product as p where p.status=true")
	List<Product> findAllProducts();
	@Query("select p from Product as p where p.brand.brand_name=:product_brand and p.status=true")
	List<Product> findByProductBrand(@Param("product_brand")String product_brand);  // Find products by brand
	
	@Query("select p from Product as p where p.product_name like Concat('%',:product_name,'%') and p.status=true")
    List<Product> findByProductNameContainingIgnoreCase(@Param("product_name") String product_name);  // Search products by name
	
	@Query("SELECT p FROM Product p WHERE p.brand.brand_name = :brand and p.status=true")
    Page<Product> findAllProductsByBrand(@Param("brand") String brand, Pageable pageable);
	@Query("SELECT p FROM Product p WHERE p.brand.brand_name = :brand and p.status=true")
    List<Product> findAllProductsByBrand(@Param("brand") String brand);
	
	@Query("SELECT p FROM Product p WHERE p.category.category_name = :category and p.status=true")
    Page<Product> findAllProductsByCategory(@Param("category") String category, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.category_name = :category and p.status=true")
    List<Product> findAllProductsByCategory(@Param("category") String category);
	
	@Query("select p from Product p where p.product_id=:id and p.status=true")
	Product findProductById(@Param("id")int id);
	
	@Query("select p.image_url from Product p where p.product_id=:id and p.status=true")
	String getImageURLByProductId(@Param("id")int id);
}
