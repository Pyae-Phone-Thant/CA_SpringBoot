package sg.edu.nus.shoopingcart.service;

//Author Pyae Phone Thant

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.interfacemethods.ProductReviewInterface;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.model.ProductReview;
import sg.edu.nus.shoopingcart.repository.ProductReviewRepository;

@Service
public class ProductReviewImplementation implements ProductReviewInterface {
	@Autowired
	private ProductReviewRepository prodReviewRepo;
	public List<ProductReview> getProductReviewsByProductId(int productId){
		return prodReviewRepo.findProductReviewByProductId(productId);
	}
	public void saveProductReview(ProductReview productReview) {
		productReview.setCreated_date(LocalDateTime.now());
		prodReviewRepo.save(productReview);
	}
	public List<Product> getBestReviewProducts(){
		return prodReviewRepo.findBestReviewProducts();
	}
	public long getTotalProductReviewsCount() {
		return prodReviewRepo.count();
	}
}
