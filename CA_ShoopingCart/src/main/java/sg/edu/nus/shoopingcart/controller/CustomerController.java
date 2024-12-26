package sg.edu.nus.shoopingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.shoopingcart.interfacemethods.CartInterface;
import sg.edu.nus.shoopingcart.interfacemethods.CustomerInterface;
import sg.edu.nus.shoopingcart.model.CartItem;
import sg.edu.nus.shoopingcart.model.Customer;
import sg.edu.nus.shoopingcart.service.CartImplementation;
import sg.edu.nus.shoopingcart.service.CustomerImplementation;
import sg.edu.nus.shoopingcart.service.EncryptDecryptImplementation;
import sg.edu.nus.shoopingcart.service.RegistrationImplementation;

//Author: Yuchen, YiChi, RongShu
@Controller
public class CustomerController {

	@Autowired
	private CustomerInterface customerService;

	@Autowired
	private CartInterface cartService;

	@Autowired
	private RegistrationImplementation registrationService;
	@Autowired
	private EncryptDecryptImplementation encryptDecryptService = new EncryptDecryptImplementation();

	@Autowired
	public void setService(CustomerImplementation cusServiceImpl, CartImplementation cartServiceImpl) {
		this.customerService = cusServiceImpl;
		this.cartService = cartServiceImpl;
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	// show all customers in database
	@RequestMapping(path = "/all")
	public @ResponseBody Iterable<Customer> getAllUsers() {
		return customerService.getAllCustomers();
	}

	// clear session
	@RequestMapping("/logout")
	public String logout(HttpSession sessionObj) {
		sessionObj.invalidate();
		return "redirect:/";
	}

	// login page
	@RequestMapping("/login")
	public String openLoginPage() {
		return "login";
	}

	// check whether the password matches the email address
	@RequestMapping("/login/judge")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession sessionObj) {

		Customer customer = customerService.getCustomerByEmail(email);

		if (customer != null && encryptDecryptService.decrypt(customer.getPassword()).equals(password)) {
			sessionObj.setAttribute("userId", customer.getCustomerId());
			sessionObj.setAttribute("userRole", customer.getRole());
			System.out.println("User ID set in session: " + customer.getCustomerId());
			List<CartItem> cartItems = cartService.getCartItems(customer.getCustomerId());
			int totalItems = cartItems.size() > 0 ? cartItems.stream().mapToInt(item -> item.getQuantity()).sum() : 0;
			sessionObj.setAttribute("totalItemsInCart", totalItems);
			String redirectToLoginFrom = (String) sessionObj.getAttribute("redirectToLoginFrom");
			if (redirectToLoginFrom != null) {
				sessionObj.removeAttribute("redirectToLoginFrom");
				if(redirectToLoginFrom.contains("/cart")) {
					String productId=(String) sessionObj.getAttribute("productId");
					if(productId!=null) {
						sessionObj.removeAttribute("productId");
						return "redirect:/product/"+productId;
					}
				}
				return "redirect:" + redirectToLoginFrom;
			}
			return "redirect:/";
		} else {
			model.addAttribute("error", "The email or password is incorrect, please try again!");
			return "login";
		}
	}

	// register page
	@RequestMapping("/register")
	public String openRegisterPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "register";
	}

	@RequestMapping("/register/judge")
	public String registerUser(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult,
			@RequestParam("passwordValid") String passwordValid, Model model) {
		if (customerService.existsByEmail(customer.getEmail())) {
			model.addAttribute("success", "The email is already exist!");
			return "register";
		}

		// Check if passwords match
		if (!customer.getPassword().equals(passwordValid)) {
			model.addAttribute("passwordMismatch", true);
			return "register";
		} else {
			if (bindingResult.hasErrors()) {
				return "register";
			}
		}
		customer.setRole("customer");
		registrationService.registerCustomerAndCreateCart(customer);

		model.addAttribute("success", "Successfully Registered!");
		return "login";
	}

	/*
	 * // Might change to this model.addAttribute("customer", registeredCustomer);
	 * return "registration-success"; // Return a view name after successful
	 * registration
	 */
	@GetMapping("/view-profile")
    public String openUserProfile(HttpSession sessionObj, Model model) {
        long userId = (long) sessionObj.getAttribute("userId");
        Customer customer = customerService.getCustomerById(userId);
        model.addAttribute("customer", customer);
        return "view-profile";
    }

    @PostMapping("/view-profile")
    public String updateUserProfile(@Valid @RequestParam("email") String email, 
                                    @RequestParam("username") String username,
                                    @RequestParam("phoneNumber") String phoneNumber, 
                                    @RequestParam("address") String address,
                                    HttpSession sessionObj, Model model) {
        long userId = (long) sessionObj.getAttribute("userId");
        String errorMessage = customerService.updateUserProfile(userId, email, username, phoneNumber, address);
        
        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
        } else {
            model.addAttribute("success", "Successfully saved!");
        }
        
        Customer customer = customerService.getCustomerById(userId);
        model.addAttribute("customer", customer);
        return "view-profile";
    }

    @GetMapping("/change-password")
    public String openChangePasswordPage(HttpSession sessionObj, Model model) {
        long userId = (long) sessionObj.getAttribute("userId");
        Customer customer = customerService.getCustomerById(userId);
        model.addAttribute("customer", customer);
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @RequestParam("password") String password,
                                 @RequestParam("new_password") String newPassword,
                                 @RequestParam("new_password_repeat") String newPasswordRepeat, 
                                 HttpSession sessionObj, 
                                 Model model, 
                                 RedirectAttributes redirectAttributes) {
        long userId = (long) sessionObj.getAttribute("userId");
        String errorMessage = customerService.changeUserPassword(userId, password, newPassword, newPasswordRepeat);
        
        if (errorMessage != null) {
            model.addAttribute("error", errorMessage);
            return "change-password";
        }
        
        redirectAttributes.addFlashAttribute("success", "Password successfully changed!");
        return "redirect:/change-password";
    }
	// Show forgot password page
	@GetMapping("/password/forgot")
	public String showForgotPasswordPage() {
	    return "forgot-password";
	}

	// Handle forgot password request, input email and send verification code
	@PostMapping("/password/forgot")
	public String processForgotPassword(@RequestParam("email") String email, Model model) {
	    Customer customer = customerService.getCustomerByEmail(email); 
	    if (customer == null) {
	        model.addAttribute("error", "Email does not exist!");
	        return "forgot-password";
	    }

	    // Simulate sending verification code, fixed code "111111"
	    customer.setResetToken("111111");
	    customerService.saveCustomer(customer);

	    model.addAttribute("email", email);
	    return "redirect:/password/verify?email=" + email; // Redirect to verification code page
	}

	// Show the verification code input page
	@GetMapping("/password/verify")
	public String showVerifyCodePage(@RequestParam("email") String email, Model model) {
	    model.addAttribute("email", email);  // Pass the email to the page
	    return "verify-code";  // Return the verification code input page
	}

	// Handle the verification code check
	@PostMapping("/password/verify")
	public String verifyCode(@RequestParam("email") String email,
	                         @RequestParam("token") String token,
	                         Model model) {
	    Customer customer = customerService.getCustomerByEmail(email);
	    if (customer == null || !customer.getResetToken().equals(token)) {
	        model.addAttribute("error", "Invalid verification code.");
	        model.addAttribute("email", email);
	        return "verify-code";  // Verification failed, return to the verification code page
	    }

	    // Verification succeeded, redirect to the reset password page
	    return "redirect:/password/reset?email=" + email;  // Redirect to reset password page with email in URL
	}

	// Show reset password page
	@GetMapping("/password/reset")
	public String showResetPasswordPage(@RequestParam("email") String email, Model model) {
	    model.addAttribute("email", email);
	    return "reset-password";
	}

	// Handle reset password request
	@PostMapping("/password/reset")
	public String processResetPassword(@RequestParam("email") String email,
	                                   @RequestParam("new_password") String newPassword,
	                                   @RequestParam("confirm_password") String confirmPassword,
	                                   Model model) {
	

	    // Verify that the new password and confirmation password match
	    if (!newPassword.equals(confirmPassword)) {
	        model.addAttribute("error", "Passwords do not match!");
	        model.addAttribute("email", email);
	        return "reset-password";
	    }
	    Customer customer = customerService.getCustomerByEmail(email); 
	    customerService.updatePassword(customer, newPassword);

	    // Encrypt the new password and save
	    customerService.updatePassword(customer, newPassword);

	    model.addAttribute("success", "Password reset successfully!");
	    return "login";  // Redirect to login page after successful password reset
	}
}
