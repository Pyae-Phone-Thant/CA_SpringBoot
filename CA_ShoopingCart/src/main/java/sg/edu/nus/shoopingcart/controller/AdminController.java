package sg.edu.nus.shoopingcart.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.shoopingcart.interfacemethods.BrandInterface;
import sg.edu.nus.shoopingcart.interfacemethods.CartInterface;
import sg.edu.nus.shoopingcart.interfacemethods.CategoryInterface;
import sg.edu.nus.shoopingcart.interfacemethods.CustomerInterface;
import sg.edu.nus.shoopingcart.interfacemethods.OrderHistoryInterface;
import sg.edu.nus.shoopingcart.interfacemethods.ProductInterface;
import sg.edu.nus.shoopingcart.interfacemethods.ProductReviewInterface;
import sg.edu.nus.shoopingcart.model.Brand;
import sg.edu.nus.shoopingcart.model.CartItem;
import sg.edu.nus.shoopingcart.model.Category;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.service.BrandImplementation;
import sg.edu.nus.shoopingcart.service.CartImplementation;
import sg.edu.nus.shoopingcart.service.CategoryImplementation;
import sg.edu.nus.shoopingcart.service.CustomerImplementation;
import sg.edu.nus.shoopingcart.service.OrderHistoryImplementation;
import sg.edu.nus.shoopingcart.service.ProductImplementation;
import sg.edu.nus.shoopingcart.service.ProductReviewImplementation;

//Author: Pyae Phone Thant
@Controller
@RequestMapping(path = "admin")
public class AdminController {
	@Autowired
	private ProductInterface productService;
	@Autowired
	private CartInterface cartService;
	@Autowired
	private CustomerInterface customerService;
	@Autowired
	private OrderHistoryInterface orderHistoryService;
	@Autowired
	private ProductReviewInterface productReviewService;
	@Autowired
	private CategoryInterface categoryService;
	@Autowired
	private BrandInterface brandService;

	@Autowired
	public void setServices(ProductImplementation prodServiceImpl, CartImplementation cartItemServiceImpl,
			CustomerImplementation customerServiceImpl, OrderHistoryImplementation orderHistoryServiceImpl,
			ProductReviewImplementation productReviewServiceImpl, CategoryImplementation categoryServiceImpl,
			BrandImplementation brandServiceImpl) {
		this.productService = prodServiceImpl;
		this.customerService = customerServiceImpl;
		this.cartService=cartItemServiceImpl;
		this.productReviewService=productReviewServiceImpl;
		this.orderHistoryService=orderHistoryServiceImpl;
		this.categoryService=categoryServiceImpl;
		this.brandService=brandServiceImpl;
	}
		
	@GetMapping("/dashboard")
	public String adminDashboard(Model model) {
		long totalProducts=productService.getTotalProductCount();
		long totalReviews=productReviewService.getTotalProductReviewsCount();
		long totalOrders=orderHistoryService.getTotalOrdersCount();
		long totalUsers=customerService.getTotalCustomersCount();
		long totalCategories=categoryService.getTotalCategoriesCount();
		long totalBrands=brandService.getTotalBrandsCount();
		model.addAttribute("totalProducts",totalProducts);
		model.addAttribute("totalReviews",totalReviews);
		model.addAttribute("totalOrders",totalOrders);
		model.addAttribute("totalUsers",totalUsers);
		model.addAttribute("totalCategories",totalCategories);
		model.addAttribute("totalBrands",totalBrands);
		return "admin-dashboard";
	}

	@GetMapping("/productlist")
	public String adminProductList(HttpSession session,Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productList", productList);
		if((String)session.getAttribute("returnMessage")!=null) {
			model.addAttribute("returnMessage",(String)session.getAttribute("returnMessage"));
			session.removeAttribute("returnMessage");
		}
		if((String)session.getAttribute("returnAlertMessage")!=null) {
			model.addAttribute("returnAlertMessage",(String)session.getAttribute("returnAlertMessage"));
			session.removeAttribute("returnAlertMessage");
		}
		return "admin-productlist";
	}

	@GetMapping("/create/product")
	public String createProduct(Model model) {
		model.addAttribute("product",new Product());
		model.addAttribute("categoriesL",categoryService.findAllCategories());
		model.addAttribute("brandsL",brandService.findAllBrands());
		return "admin-create-product";
	}

	@PostMapping("/create/product")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, 
			HttpSession session,@RequestParam("image1") MultipartFile image1,
			@RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3,
			@RequestParam("image4") MultipartFile image4, @RequestParam("image5") MultipartFile image5,
			@RequestParam("image6") MultipartFile image6,Model model) {

		try {
			Long userId = (Long) session.getAttribute("userId");
			
			createImageInFolder(product,image1,1,model);
			createImageInFolder(product,image2,2,model);
			createImageInFolder(product,image3,3,model);
			createImageInFolder(product,image4,4,model);
			createImageInFolder(product,image5,5,model);
			createImageInFolder(product,image6,6,model);
			if(bindingResult.hasErrors()) {
			    model.addAttribute("categoriesL",categoryService.findAllCategories());
				model.addAttribute("brandsL",brandService.findAllBrands());
				return  "admin-create-product";
			}
			List<String> images=Arrays.asList(product.getImage_url().split(","));
			for(int i=1;i<=6&&images.size()>0;i++) {
				if(!images.contains(product.getProduct_name()+"_"+i+".jpg")) {
					deleteFile(product.getProduct_name()+"_"+i+".jpg");
				}
			}
					
			product.setCreatedBy(customerService.getCustomerById(userId));
			product.setCreated_date(LocalDateTime.now());
			product.setStatus(true);
			productService.saveProduct(product);
			session.setAttribute("returnMessage","Created Successfully.");
			return "redirect:/admin/productlist";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	@GetMapping("/edit/product/{id}")
	public String editProduct(@PathVariable("id") int id,Model model) {
	    
	    Product product=new Product();
	    product=productService.getProductById(id);
		model.addAttribute("product",product);
		model.addAttribute("categoriesL",categoryService.findAllCategories());
		model.addAttribute("brandsL",brandService.findAllBrands());
		return "admin-edit-product";
	}

	@PostMapping("/edit/product/{id}")
	public String editProduct(@PathVariable("id") int id,@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, 
			HttpSession session,@RequestParam("image1") MultipartFile image1,
			@RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3,
			@RequestParam("image4") MultipartFile image4, @RequestParam("image5") MultipartFile image5,
			@RequestParam("image6") MultipartFile image6,Model model) {

		try {
			Long userId = (Long) session.getAttribute("userId");
			
			createImageInFolder(product,image1,1,model);
			createImageInFolder(product,image2,2,model);
			createImageInFolder(product,image3,3,model);
			createImageInFolder(product,image4,4,model);
			createImageInFolder(product,image5,5,model);
			createImageInFolder(product,image6,6,model);
			if(bindingResult.hasErrors()) {
			    model.addAttribute("categoriesL",categoryService.findAllCategories());
				model.addAttribute("brandsL",brandService.findAllBrands());
				return  "admin-edit-product";
			}
			List<String> images=Arrays.asList(product.getImage_url().split(","));
			for(int i=1;i<=6&&images.size()>0;i++) {
				if(!images.contains(product.getProduct_name()+"_"+i+".jpg")) {
					deleteFile(product.getProduct_name()+"_"+i+".jpg");
				}
			}					
			product.setUpdatedBy(customerService.getCustomerById(userId));
			product.setUpdated_date(LocalDateTime.now());
			product.setStatus(true);
			productService.saveProduct(product);
			session.setAttribute("returnMessage","Edited Successfully.");
			return "redirect:/admin/productlist";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	@GetMapping("/delete/product/{id}")
	public String deleteProduct(@PathVariable("id")int id,HttpSession session,Model model) {
		Long userId = (Long) session.getAttribute("userId");
		List<CartItem> cartItems=cartService.getCartItemsByProductId(id);
		if(cartItems.size()>0) {
			session.setAttribute("returnAlertMessage","Cannot delete this product. Existed in customer's shopping cart.");
			return "redirect:/admin/productlist";
		}
		Product product=new Product();
	    product=productService.getProductById(id);
	    product.setStatus(false);
	    product.setUpdatedBy(customerService.getCustomerById(userId));
		product.setUpdated_date(LocalDateTime.now());
		productService.saveProduct(product);
		session.setAttribute("returnMessage","Deleted Successfully.");
		return "redirect:/admin/productlist";
	}
	
	@GetMapping("/categorylist")
	public String adminCategoryList(HttpSession session,Model model) {
		List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("categoryList", categories);
		model.addAttribute("category",new Category());
		if((String)session.getAttribute("returnMessage")!=null) {
			model.addAttribute("returnMessage",(String)session.getAttribute("returnMessage"));
			session.removeAttribute("returnMessage");
		}
		if((String)session.getAttribute("returnAlertMessage")!=null) {
			model.addAttribute("category",(Category)session.getAttribute("category"));
			model.addAttribute("returnAlertMessage",(String)session.getAttribute("returnAlertMessage"));
			session.removeAttribute("returnAlertMessage");
		}
		return "admin-category-list";
	}
	@PostMapping("/create/category")
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, HttpSession session,
			Model model) {
		try {

			Long userId = (Long) session.getAttribute("userId");
			
			if(bindingResult.hasErrors()) {
				return  "admin-category-list";
			}				
			int count=categoryService.findCategoryByName(category.getCategory_name());
			if(count>0) {
				session.setAttribute("returnAlertMessage","Cannot create this category. Same category is already existed.");
				session.setAttribute("category", category);
				return "redirect:/admin/categorylist";
			}
			category.setCreatedBy(customerService.getCustomerById(userId));
			category.setCreated_date(LocalDateTime.now());
			category.setStatus(true);
			categoryService.saveCategory(category);
			session.setAttribute("returnMessage","Created Successfully.");
			return "redirect:/admin/categorylist";
		}
		catch(Exception e) {
			return "error";
		}
	}
	@GetMapping("/brandlist")
	public String adminBrandList(HttpSession session,Model model) {
		List<Brand> brands = brandService.findAllBrands();
		model.addAttribute("brandList", brands);
		model.addAttribute("brand",new Brand());
		if((String)session.getAttribute("returnMessage")!=null) {
			model.addAttribute("returnMessage",(String)session.getAttribute("returnMessage"));
			session.removeAttribute("returnMessage");
		}
		if((String)session.getAttribute("returnAlertMessage")!=null) {
			model.addAttribute("brand",(Brand)session.getAttribute("brand"));
			model.addAttribute("returnAlertMessage",(String)session.getAttribute("returnAlertMessage"));
			session.removeAttribute("returnAlertMessage");
		}
		return "admin-brand-list";
	}
	@PostMapping("/create/brand")
	public String createBrand(@Valid @ModelAttribute("brand") Brand brand, BindingResult bindingResult, HttpSession session,
			Model model) {
		try {

			Long userId = (Long) session.getAttribute("userId");
			
			if(bindingResult.hasErrors()) {
				return  "admin-brand-list";
			}				
			int count=brandService.findBrandByName(brand.getBrand_name());
			if(count>0) {
				session.setAttribute("returnAlertMessage","Cannot create this brand. Same brand is already existed.");
				session.setAttribute("brand", brand);
				return "redirect:/admin/brandlist";
			}
			brand.setCreatedBy(customerService.getCustomerById(userId));
			brand.setCreated_date(LocalDateTime.now());
			brand.setStatus(true);
			brandService.saveBrand(brand);
			session.setAttribute("returnMessage","Created Successfully.");
			return "redirect:/admin/brandlist";
		}
		catch(Exception e) {
			return "error";
		}
	}
	public void createImageInFolder(Product product,MultipartFile image,int num,Model model) {
		String CURRENT_DIR = System.getProperty("user.dir");
		String UPLOAD_DIR = CURRENT_DIR+"/src/main/resources/static/images/";
		try {
			if(image != null && !image.isEmpty()) {
				String imageName=product.getProduct_name()+"_"+num+".jpg";
				File file = new File(UPLOAD_DIR + imageName);
				image.transferTo(file);	
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	public void deleteFile(String filePath) {
		String CURRENT_DIR = System.getProperty("user.dir");
		String UPLOAD_DIR = CURRENT_DIR+"/src/main/resources/static/images/";
		filePath=UPLOAD_DIR+filePath;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete(); // returns true if the file was deleted successfully
        }
    }
}
