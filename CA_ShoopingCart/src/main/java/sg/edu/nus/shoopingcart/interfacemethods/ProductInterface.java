package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.shoopingcart.model.Product;

public interface ProductInterface {
	public List<Product> getAllProducts();
	public List<Product> searchProducts(String keyword);
	public List<Product> filterByBrand(String brand);
	public Page<Product> getAllProducts(int page, int size);
	public List<String> getAllProductBrands();
	public Page<Product> getAllProductsByBrand(int page,int size, String brand);
	public Page<Product> getAllProductsByCategory(int page,int size, String category);
	public List<String> getAllProductCategories();
	public Product getProductById(int id);
	public String getProductImages(int id);
}
