package sg.edu.nus.shoopingcart.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.shoopingcart.interfacemethods.CartInterface; // Ensure this import if using @Valid
import sg.edu.nus.shoopingcart.interfacemethods.CustomerInterface;
import sg.edu.nus.shoopingcart.interfacemethods.OrderDetailsInterface;
import sg.edu.nus.shoopingcart.interfacemethods.OrderHistoryInterface;
import sg.edu.nus.shoopingcart.interfacemethods.ProductInterface;
import sg.edu.nus.shoopingcart.model.CartItem; // Import for @RequestBody
import sg.edu.nus.shoopingcart.model.Customer;
import sg.edu.nus.shoopingcart.model.OrderDetails;
import sg.edu.nus.shoopingcart.model.OrderHistory;
import sg.edu.nus.shoopingcart.model.PaymentMethod;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.service.CartImplementation;
import sg.edu.nus.shoopingcart.service.CustomerImplementation;
import sg.edu.nus.shoopingcart.service.OrderDetailsImplementation;
import sg.edu.nus.shoopingcart.service.OrderHistoryImplementation;
import sg.edu.nus.shoopingcart.service.PaymentService;
import sg.edu.nus.shoopingcart.service.ProductImplementation;

// Author: Ong Xin Min
@Controller
public class CheckOutController {

    @Autowired
    private CustomerInterface cservice;

    @Autowired
    private OrderHistoryInterface oservice;

    @Autowired
    private OrderDetailsInterface odservice;

    @Autowired
    private CartInterface cartService;

    @Autowired
    private PaymentService paymentService;
    public static double totalPrice;

    @Autowired
    private ProductInterface pservice;

    @Autowired
    public void setCartService(CartImplementation cartImplementation) {
        this.cartService = cartImplementation;
    }

    public void setCustomerService(CustomerImplementation cserviceImpl) {
        this.cservice = cserviceImpl;
    }

    public void setOrderHistoryService(OrderHistoryImplementation oserviceImpl) {
        this.oservice = oserviceImpl;
    }

    public void setOrderDetailsService(OrderDetailsImplementation odserviceImpl) {
        this.odservice = odserviceImpl;
    }

    public void setProductService(ProductImplementation pserviceImpl) {
        this.pservice = pserviceImpl;
    }

    @GetMapping("/checkout")
    public String showCheckoutPage(HttpSession session, @RequestParam(required = false) String processCheckOut, Model model) {

        // Check if processCheckOut is true
        if ("true".equals(processCheckOut)) {
            Long userId = (Long) session.getAttribute("userId");

            List<CartItem> items = cartService.getCartItems(userId);
            Customer customer = cservice.getCustomerById(userId);

            model.addAttribute("customerId", userId);
            model.addAttribute("orderItems", items); // Fetch items in cart
            model.addAttribute("customer", customer);
            model.addAttribute("totalAmount", calculateTotalAmount(items)); // Calculate total
            List<PaymentMethod> paymentMethods = paymentService.getAllPaymentMethods();
            model.addAttribute("paymentMethods", paymentMethods);
            session.setAttribute("processCheckOut", false);
            return "check-out"; // Thymeleaf template name
        } else {
            // Handle case where processCheckOut is not true
            session.setAttribute("errorMessage", "Sorry, you cannot access this page directly.");
            return "redirect:/cart"; // Redirect back to cart

        }
    }

    @PostMapping("/checkout")
    public String processCheckout(HttpSession session,
            @RequestParam("paymentMethods") PaymentMethod selectedPaymentMethod, Model model) {
        //For unsuccessful payment, did not go through to /checkout/success
        String errorMessage2 = (String) session.getAttribute("errorMessage2");

        //For unsuccessful payment, did not go through to /checkout/success
        if (errorMessage2 != null) {
            model.addAttribute("errorMessage", errorMessage2);
            session.removeAttribute("errorMessage2");
            return "redirect:/checkout";
        } else {

            Long userId = (Long) session.getAttribute("userId");
            session.setAttribute("selectedPaymentMethod", selectedPaymentMethod.getDisplayName());
            List<CartItem> items = cartService.getCartItems(userId);

            if (items == null || items.isEmpty()) {
                model.addAttribute("errorMessage", "Your cart is empty.");
                return "redirect:/cart";
            }
            //new
            session.setAttribute("paymentCompleted", true);
            System.out.println(session.getAttribute("paymentCompleted"));
            //newEnd

            System.out.println("Redirecting to success page...");
            if (selectedPaymentMethod.getDisplayName().equals("PAYPAL")) {
                return "index";
            }
        }
        return "redirect:/checkout/success"; // Redirect after successful checkout

    }

    @GetMapping("/checkout/success")
    public String showSuccessPage(HttpSession session, Model model) {

        //new
        Boolean paymentCompleted = (Boolean) session.getAttribute("paymentCompleted");
        if (paymentCompleted != null && paymentCompleted) {
            session.removeAttribute("paymentCompleted");
            Long customerId = (Long) session.getAttribute("userId");

            OrderHistory orderHistory = new OrderHistory();
            Customer customer = cservice.getCustomerById(customerId);

            orderHistory.setCustomer(customer);

            orderHistory.setOrderDate(LocalDateTime.now());
            orderHistory.setPaymentMethod(
                    (String) session.getAttribute("selectedPaymentMethod"));

            // save orderHistory to the database
            oservice.saveOrder(orderHistory);

            List<CartItem> items = cartService.getCartItems(customerId);

            List<OrderDetails> successOrderDetails = new ArrayList<>();
            for (CartItem item : items) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderHistory(orderHistory);
                orderDetails.setProduct(item.getProduct());
                orderDetails.setQuantity(item.getQuantity());
                orderDetails.setSalePrice(item.getProduct().getSale_price());
                // Save each OrderDetail to the List
                successOrderDetails.add(orderDetails);

            }

            //Setting the sum of quantity of products purchased to product db
            for (int i = 0; i < successOrderDetails.size(); i++) {
                Product orderProduct = successOrderDetails.get(i).getProduct();

                int productId = orderProduct.getProduct_id();
                int currentQuantity = pservice.getProductById(productId).getQuantity();
                int purchaseQuantity = successOrderDetails.get(i).getQuantity();
                int newQuantity = currentQuantity - purchaseQuantity;
                pservice.getProductById(productId).setQuantity(newQuantity);
            }
            odservice.saveAllOrderDetails(successOrderDetails);

            model.addAttribute("totalAmount", calculateTotalAmount(items));
            String selectedPaymentMethod = (String) session.getAttribute("selectedPaymentMethod");
            String paymentMethodUrl = paymentService.getPaymentUrl(selectedPaymentMethod);
            model.addAttribute("paymentMethodUrl", paymentMethodUrl);
            model.addAttribute("customer", customer);
            model.addAttribute("orderHistory", orderHistory);
            model.addAttribute("successOrderDetails", successOrderDetails);
            model.addAttribute("message", "Thank you for your purchase!");

            // Check if both saves were successful
            boolean orderSavedSuccessfully = (orderHistory != null && !successOrderDetails.isEmpty());

            // Clear the cart only if the order was saved successfully
            if (orderSavedSuccessfully) {
                cartService.clearCart(customerId);
                session.setAttribute("totalItemsInCart", 0);
                return "success";
            } else {
                session.setAttribute("errorMessage2", "Failed to complete your purchase.");
                return "redirect:/checkout"; // Redirect back to checkout or show error
            }

        } else {
            session.setAttribute("errorMessage", "Sorry, you cannot access this page directly. Please check your purchase history or shopping cart. Thank you!");
            return "redirect:/cart";
        }

    }

    private double calculateTotalAmount(List<CartItem> items) {
        // Calculate total based on order items
        double total = 0;
        for (CartItem item : items) {
            double quantity = item.getQuantity();
            double price = item.getProduct().getSale_price(); // Assuming getPrice() returns BigDecimal

            // Calculate the total for this item and add it to the total
            total = total + quantity * price;
        }
        this.totalPrice = total;
        return total; // Return the total amount as BigDecimal
    }
}
