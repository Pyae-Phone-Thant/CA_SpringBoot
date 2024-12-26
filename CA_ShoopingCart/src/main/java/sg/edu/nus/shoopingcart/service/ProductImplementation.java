package sg.edu.nus.shoopingcart.service;

//Author Pyae Phone Thant

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
	
	public long getTotalProductCount() {
		return productRepository.findTotalCount();
	}

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }

    public List<Product> filterByBrand(String brand) {
        return productRepository.findByProductBrand(brand);
    }
    public Page<Product> getAllProductsByBrand(int page, int size,String brand) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductsByBrand(brand,pageable);
    }
    public Page<Product> getAllProductsByBrand(int page, int size,String brand,String sort) {
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findAllProductsByBrand(brand);
        if(sort.equals("a-z")) {
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getProduct_name))
                    .collect(Collectors.toList());

        }
        else if(sort.equals("z-a")){
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getProduct_name).reversed())
                    .collect(Collectors.toList());
        }
        else if(sort.equals("0-n")) {
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getSale_price))
                    .collect(Collectors.toList());
        }
        else if(sort.equals("n-0")) {
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getSale_price).reversed())
                    .collect(Collectors.toList());
        }
        
        // Convert List to Page
        int start = (int) pageable.getOffset();  // Start index for the current page
        int end = Math.min((start + pageable.getPageSize()), products.size());  // End index (taking the size into account)
        List<Product> pagedProducts = products.subList(start, end);  // Extract the products for the current page

        // Return the paginated Page object
        Page<Product> sortedPage = new PageImpl<>(pagedProducts, pageable, products.size());
        
        return sortedPage;
    }
    public Page<Product> getAllProductsByCategory(int page, int size,String category) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductsByCategory(category,pageable);
    }
    public Page<Product> getAllProductsByCategory(int page, int size,String category,String sort) {
        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productRepository.findAllProductsByCategory(category);
        if(sort.equals("a-z")) {
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getProduct_name))
                    .collect(Collectors.toList());

        }
        else if(sort.equals("z-a")){
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getProduct_name).reversed())
                    .collect(Collectors.toList());
        }
        else if(sort.equals("0-n")) {
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getSale_price))
                    .collect(Collectors.toList());
        }
        else if(sort.equals("n-0")) {
        	products = products.stream()
                    .sorted(Comparator.comparing(Product::getSale_price).reversed())
                    .collect(Collectors.toList());
        }
        
        // Convert List to Page
        int start = (int) pageable.getOffset();  // Start index for the current page
        int end = Math.min((start + pageable.getPageSize()), products.size());  // End index (taking the size into account)
        List<Product> pagedProducts = products.subList(start, end);  // Extract the products for the current page

        // Return the paginated Page object
        Page<Product> sortedPage = new PageImpl<>(pagedProducts, pageable, products.size());
        
        return sortedPage;
    }
    public Product getProductById(int id) {
    	return productRepository.findProductById(id);
    }
    public String getProductImages(int id) {
    	return productRepository.getImageURLByProductId(id);
    }
    public List<Product> getProductsByProductNameContainingIgnoreCase(String name){
    	return productRepository.findByProductNameContainingIgnoreCase(name);
    }
    public void saveProduct(Product product) {
    	productRepository.save(product);
    }
}
