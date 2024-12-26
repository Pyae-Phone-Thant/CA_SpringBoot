package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Product;
//Aurthor Pyae Phone Thant
public interface ProductInterface {
	public long getTotalProductCount();
	public List<Product> getAllProducts();
	public List<Product> searchProducts(String keyword);
	public List<Product> filterByBrand(String brand);
	public Page<Product> getAllProductsByBrand(int page,int size, String brand);
	public Page<Product> getAllProductsByBrand(int page,int size, String brand,String sort);
	public Page<Product> getAllProductsByCategory(int page, int size,String category);
	public Page<Product> getAllProductsByCategory(int page,int size, String category,String sort);
	public Product getProductById(int id);
	public String getProductImages(int id);
	public List<Product> getProductsByProductNameContainingIgnoreCase(String name);
	public void saveProduct(Product product);
}
