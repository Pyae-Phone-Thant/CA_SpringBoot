package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.model.ProductReview;
//Author Pyae Phone Thant
public interface ProductReviewInterface {
	public List<ProductReview> getProductReviewsByProductId(int productId);
	public void saveProductReview(ProductReview productReview);
	public List<Product> getBestReviewProducts();
	public long getTotalProductReviewsCount();
}
