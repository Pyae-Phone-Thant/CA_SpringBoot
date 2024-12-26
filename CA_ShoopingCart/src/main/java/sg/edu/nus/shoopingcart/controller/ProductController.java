package sg.edu.nus.shoopingcart.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.shoopingcart.interfacemethods.CustomerInterface;
import sg.edu.nus.shoopingcart.interfacemethods.ProductInterface;
import sg.edu.nus.shoopingcart.interfacemethods.ProductReviewInterface;
import sg.edu.nus.shoopingcart.model.Customer;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.model.ProductReview;
import sg.edu.nus.shoopingcart.model.ProductViewForSearch;
import sg.edu.nus.shoopingcart.service.CustomerImplementation;
import sg.edu.nus.shoopingcart.service.EncryptDecryptImplementation;
import sg.edu.nus.shoopingcart.service.ProductImplementation;
import sg.edu.nus.shoopingcart.service.ProductReviewImplementation;

//Author: Pyae Phone Thant
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@Controller
public class ProductController {
	@Autowired
	private ProductInterface productService;
	@Autowired
	private ProductReviewInterface productReviewService;
	@Autowired
	private CustomerInterface customerService;
	
	@Autowired
	public void setServices(ProductImplementation prodServiceImpl,ProductReviewImplementation prodReviewServiceImpl, CustomerImplementation customerServiceImpl) {
		this.productService=prodServiceImpl;
		this.productReviewService=prodReviewServiceImpl;
		this.customerService=customerServiceImpl;
	}
	
	
	@RequestMapping("/")
	public String homePage(Model model) {
	    List<String> images=new ArrayList<String>();
	    images.add("homepage_photo_3.jpg");
	    images.add("homepage_photo_2.jpg");
	    images.add("homepage_photo_1.jpg");
	    model.addAttribute("images",images);
	    List<Product> bestReviewProducts=new ArrayList<Product>();
	    bestReviewProducts=productReviewService.getBestReviewProducts();
	    model.addAttribute("bestReviewProducts",bestReviewProducts);
	    
		return "home-page";
	}
	
	@GetMapping("/products/category/{category}")
	public String getProductsByCategory(@RequestParam(defaultValue = "1") int page,
	                          @RequestParam(defaultValue = "4") int size,
	                          @RequestParam(defaultValue="a-z") String sort,
	                          @PathVariable("category") String category,
	                          Model model) {
	    // Fetch paginated products
	    Page<Product> productPage = productService.getAllProductsByCategory(page-1, size,category,sort);

	    // Add paginated data to the model
	    model.addAttribute("productPage", productPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", productPage.getTotalPages());
	    model.addAttribute("totalItems", productPage.getTotalElements()); // Total products
	    model.addAttribute("itemsPerPage", productPage.getNumberOfElements()); // Products per page
	    model.addAttribute("pageSize", size); // Pass the page size to the view
	    model.addAttribute("title",category);
	    model.addAttribute("sortBy",sort);
	    model.addAttribute("route","/category/"+category);
	    
	    //int cartItemCount = cartService.getCartItemCount();
	    model.addAttribute("cartItemCount", 0);

	    return "product-list";
	}
	
	@GetMapping("/products/brand/{brandName}")
	public String getProductsByBrand(@RequestParam(defaultValue = "1") int page,
	                          @RequestParam(defaultValue = "4") int size,
	                          @RequestParam(defaultValue="a-z") String sort,
	                          @PathVariable("brandName") String brandName,
	                          Model model) {
	    // Fetch paginated products
	    Page<Product> productPage = productService.getAllProductsByBrand(page-1, size,brandName,sort);

	    // Add paginated data to the model
	    model.addAttribute("productPage", productPage);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", productPage.getTotalPages());
	    model.addAttribute("totalItems", productPage.getTotalElements()); // Total products
	    model.addAttribute("itemsPerPage", productPage.getNumberOfElements()); // Products per page
	    model.addAttribute("pageSize", size); // Pass the page size to the view
	    model.addAttribute("title",brandName);
	    model.addAttribute("sortBy",sort);
	    model.addAttribute("route","/brand/"+brandName);
	    
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
        
        List<ProductReview> productReviews=productReviewService.getProductReviewsByProductId(id);
        int reviewsCount=productReviews.size();
        int maxRating=productReviews.size()>0?productReviews.stream().mapToInt(e->e.getRating()).max().getAsInt():0;
        
        model.addAttribute("product", product);
        model.addAttribute("productImages", l_productImages);
        model.addAttribute("productFeatures",l_productFeatures);
        model.addAttribute("reviewsCount",reviewsCount);
        model.addAttribute("maxRating",maxRating);
        return "product-detail";
    }
    @GetMapping("/product-review/{id}")
    public String addReview(@PathVariable("id") int id,Model model,HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
    	ProductReview productReview=new ProductReview();
    	productReview.setProduct(productService.getProductById(id));
    	productReview.setCustomer(customerService.getCustomerById(userId));
    	List<ProductReview> productReviews=productReviewService.getProductReviewsByProductId(id);
    	//productReview.setProduct_id(id);
    	//productReview.setCustomer_id(1);
    	model.addAttribute("productreview",productReview);
    	String addedReviewStatus=(String)session.getAttribute("review-added-status");
    	if(addedReviewStatus!=null&&addedReviewStatus.equals("success")) {
    		model.addAttribute("addedReview",true);
    		session.removeAttribute("review-added-status");
    	}
    	else {
    		model.addAttribute("addedReview",false);
    	}
    	model.addAttribute("reviews",productReviews);
    	return "product-review";
    }
    @PostMapping("/product-review/add")
    public String addReview(@Valid @ModelAttribute("productreview")  ProductReview productReview, BindingResult bindingResult,
			Model model,HttpSession session) {
    	if(bindingResult.hasErrors()) {
    		return "product-review";
    	}
    	productReviewService.saveProductReview(productReview);
    	session.setAttribute("review-added-status", "success");
    	return "redirect:/product-review/"+productReview.getProduct().getProduct_id();
    }

    @GetMapping("/products/search")
    @ResponseBody
    public List<ProductViewForSearch> searchProducts(@RequestParam("query") String query) 
    {
        List<Product> products = productService.getProductsByProductNameContainingIgnoreCase(query);
        List<ProductViewForSearch> pIdAndName = products.stream()
        	    .map(product -> {
        	        ProductViewForSearch view = new ProductViewForSearch();
        	        view.id = product.getProduct_id();    // Assuming getProduct_id() returns an Integer
        	        view.name = product.getProduct_name(); // Assuming getProduct_name() returns a String
        	        return view;
        	    })
        	    .collect(Collectors.toList());
        return pIdAndName;
    }

    @GetMapping("/products/filter")
    public String filterByBrand(@RequestParam("brand") String brand, Model model) {
        List<Product> products = productService.filterByBrand(brand);
        model.addAttribute("products", products);
        return "product-list";
    }
}
