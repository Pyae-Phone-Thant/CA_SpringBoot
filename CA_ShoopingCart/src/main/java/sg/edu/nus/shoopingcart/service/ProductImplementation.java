package sg.edu.nus.shoopingcart.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.interfacemethods.ProductInterface;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.repository.ProductRepository;

@Service
public class ProductImplementation implements ProductInterface {
	@Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }

    public List<Product> filterByBrand(String brand) {
        return productRepository.findByProductBrand(brand);
    }
    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
    public List<String> getAllProductBrands(){
    	return productRepository.findDistinctProductBrand();
    }
    public Page<Product> getAllProductsByBrand(int page, int size,String brand) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductsByBrand(brand,pageable);
    }
    public Page<Product> getAllProductsByCategory(int page, int size,String category) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductsByCategory(category,pageable);
    }
    public List<String> getAllProductCategories(){
    	return productRepository.findDistinctProductCategory();
    }
    public Product getProductById(int id) {
    	return productRepository.findProductById(id);
    }
    public String getProductImages(int id) {
    	return productRepository.getImageURLByProductId(id);
    }
}
