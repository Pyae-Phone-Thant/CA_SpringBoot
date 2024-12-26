package sg.edu.nus.shoopingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.model.ProductReview;
//Author Pyae Phone Thant
public interface ProductReviewRepository extends JpaRepository<ProductReview,Integer> {
	@Query("select pr from ProductReview pr where pr.product.product_id = :productId and pr.product.status=true")
	public List<ProductReview> findProductReviewByProductId(@Param("productId")int productId);
	
	@Query("select distinct (pr.product) from ProductReview pr where pr.rating=5 and pr.product.status=true")
	public List<Product> findBestReviewProducts();
}
