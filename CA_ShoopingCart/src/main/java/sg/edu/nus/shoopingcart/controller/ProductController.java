package sg.edu.nus.shoopingcart.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.shoopingcart.interfacemethods.ProductInterface;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.service.ProductImplementation;

@Controller
public class ProductController {
	@Autowired
	private ProductInterface productService;
	
	@Autowired
	public void setProductService(ProductImplementation prodServiceImpl) {
		this.productService=prodServiceImpl;
	}
	
	@RequestMapping("/")
	public String homePage(Model model) {
		
		// Fetch brands and cart item count
	    List<String> brands = productService.getAllProductBrands();
	    model.addAttribute("brands", brands);
	    
	    List<String> categories=productService.getAllProductCategories();
	    model.addAttribute("categories",categories);
	    
		return "home-page";
	}
	
	@GetMapping("/products")
	public String getProducts(@RequestParam(defaultValue = "1") int page,
	                          @RequestParam(defaultValue = "12") int size,
	                          Model model) {
	    // Fetch paginated products
	    Page<Product> productPage = productService.getAllProducts(page-1, size);

	    // Add paginated data to the model
	    model.addAttribute("productPage", productPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", productPage.getTotalPages());
	    model.addAttribute("totalItems", productPage.getTotalElements()); // Total products
	    model.addAttribute("itemsPerPage", productPage.getNumberOfElements()); // Products per page
	    model.addAttribute("pageSize", size); // Pass the page size to the view
	    
	    // Fetch brands and cart item count
	    List<String> brands = productService.getAllProductBrands();
	    model.addAttribute("brands", brands);
	    
	    List<String> categories=productService.getAllProductCategories();
	    model.addAttribute("categories",categories);
	    
	    //int cartItemCount = cartService.getCartItemCount();
	    model.addAttribute("cartItemCount", 0);

	    return "product-list";
	}
	
	@GetMapping("/products/category/{category}")
	public String getProductsByCategory(@RequestParam(defaultValue = "1") int page,
	                          @RequestParam(defaultValue = "4") int size,
	                          @PathVariable("category") String category,
	                          Model model) {
	    // Fetch paginated products
	    Page<Product> productPage = productService.getAllProductsByCategory(page-1, size,category);

	    // Add paginated data to the model
	    model.addAttribute("productPage", productPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", productPage.getTotalPages());
	    model.addAttribute("totalItems", productPage.getTotalElements()); // Total products
	    model.addAttribute("itemsPerPage", productPage.getNumberOfElements()); // Products per page
	    model.addAttribute("pageSize", size); // Pass the page size to the view
	    model.addAttribute("title",category);
	    
	    // Fetch brands and cart item count
	    List<String> brands = productService.getAllProductBrands();
	    model.addAttribute("brands", brands);
	    
	    //int cartItemCount = cartService.getCartItemCount();
	    model.addAttribute("cartItemCount", 0);

	    return "product-list";
	}
	
	@GetMapping("/products/brand/{brandName}")
	public String getProductsByBrand(@RequestParam(defaultValue = "1") int page,
	                          @RequestParam(defaultValue = "4") int size,
	                          @PathVariable("brandName") String brandName,
	                          Model model) {
	    // Fetch paginated products
	    Page<Product> productPage = productService.getAllProductsByBrand(page-1, size,brandName);

	    // Add paginated data to the model
	    model.addAttribute("productPage", productPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", productPage.getTotalPages());
	    model.addAttribute("totalItems", productPage.getTotalElements()); // Total products
	    model.addAttribute("itemsPerPage", productPage.getNumberOfElements()); // Products per page
	    model.addAttribute("pageSize", size); // Pass the page size to the view
	    model.addAttribute("title",brandName);
	    
	    // Fetch brands and cart item count
	    List<String> brands = productService.getAllProductBrands();
	    model.addAttribute("brands", brands);
	    
	    //int cartItemCount = cartService.getCartItemCount();
	    model.addAttribute("cartItemCount", 0);

	    return "product-list";
	}
	
	// Product Detail Page
    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        
        String[] array_productImages=product.getImage_url().split(",");
        List<String> l_productImages=Arrays.asList(array_productImages);
        String[] array_productFeatures=product.getProduct_feature().split(";");
        List<String> l_productFeatures=Arrays.asList(array_productFeatures);
        
        model.addAttribute("product", product);
        model.addAttribute("productImages", l_productImages);
        model.addAttribute("productFeatures",l_productFeatures);
        return "product-detail";
    }

	@GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/filter")
    public String filterByBrand(@RequestParam("brand") String brand, Model model) {
        List<Product> products = productService.filterByBrand(brand);
        model.addAttribute("products", products);
        return "product-list";
    }
}
