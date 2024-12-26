package sg.edu.nus.shoopingcart.service;
//Author Siow Xiang Ying
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.shoopingcart.interfacemethods.CartInterface;
import sg.edu.nus.shoopingcart.model.Cart;
import sg.edu.nus.shoopingcart.model.CartItem;
import sg.edu.nus.shoopingcart.model.Product;
import sg.edu.nus.shoopingcart.repository.CartItemRepository;
import sg.edu.nus.shoopingcart.repository.CartRepository;
import sg.edu.nus.shoopingcart.repository.CustomerRepository;
import sg.edu.nus.shoopingcart.repository.ProductRepository;

@Service
public class CartImplementation implements CartInterface {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new cart for new user
    @Override
    public Cart createCart(long customerId) {
        Cart cart = new Cart();
        cart.setCustomer(customerRepository.findById(customerId).get());
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        cart.setStatus("active");
        return cartRepository.save(cart);
    }

    // Get the active cart for a user
    @Override
    public Cart getActiveCart(long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    @Override
    public Cart addItemToCart(long customerId, int productId, int quantity) {
        Cart cart = getActiveCart(customerId);
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), productId); 
        Product product = productRepository.findById(productId).get();  
        
        if (existingCartItem.isPresent()) {
            // Update quantity if item already exists in the cart
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setAddedAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setAddedAt(LocalDateTime.now());
            cartItemRepository.save(cartItem);
        }

        // Update the cart's updated time
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);

    }

    @Override
    public Cart updateItemQuantityInCart(long customerId, int productId, int newQuantity) {
        // Retrieve the active cart for the given customer ID
        Cart cart = getActiveCart(customerId);
    
        // Find the existing cart item by cart ID and product ID
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), productId);
    
        // Check if the new quantity is valid (you can decide what to do with negative values)
        if (newQuantity < 0) {
            throw new IllegalArgumentException("New quantity cannot be less than zero.");
        }
    
        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
    
            // If new quantity is greater than zero, update the existing cart item
            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                cartItem.setAddedAt(LocalDateTime.now());
                cartItemRepository.save(cartItem);
            } else {
                // If new quantity is zero, remove the item from the cart
                cartItemRepository.delete(cartItem);
            }
        } else {
            // If newQuantity is zero and the item doesn't exist in the cart, do nothing
            throw new NoSuchElementException("CartItem not found for the given product ID: " + productId);
        }
    
        // Update the cart's updated time
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }
    

    @Override
    public Cart removeItemFromCart(long customerId, int productId) {

        Cart cart = getActiveCart(customerId);
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), productId);
        existingCartItem.ifPresent(item -> {
            cartItemRepository.delete(item);
            cart.getCartItems().remove(item); // Remove item from cart items list
        });

        // Update the cart's updated time
        cart.setUpdatedAt(LocalDateTime.now());
        return cartRepository.save(cart);
    }

    @Override
    public List<CartItem> getCartItems(long customerId) {
        Cart cart = getActiveCart(customerId);
        return cart.getCartItems();
    }

    // Clear all items from the active cart
    @Override
    public Cart clearCart(long customerId) {
       Cart cart = getActiveCart(customerId);
       cartItemRepository.deleteByCartId(cart.getCartId());
       cart.setUpdatedAt(LocalDateTime.now());
       return cartRepository.save(cart);
    }

    // Calculate the total price of items in the active cart for a user
    @Override
    public double getTotal(long customerId) {
        Cart cart = getActiveCart(customerId);
        double total = cart.getCartItems()
                           .stream()
                           .map(item -> item.getProduct().getSale_price() * item.getQuantity())
                           .reduce(0.0, Double::sum);
        return total;
    }
    @Override
    public List<CartItem> getCartItemsByProductId(int id){
    	return cartItemRepository.findCartItemsByProductId(id);
    }
}
