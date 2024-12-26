package sg.edu.nus.shoopingcart.interfacemethods;

import java.util.List;

import sg.edu.nus.shoopingcart.model.Cart;
import sg.edu.nus.shoopingcart.model.CartItem;

public interface CartInterface {
    Cart createCart(long customerId);
    Cart getActiveCart(long customerId);
    Cart addItemToCart(long customerId, int productId, int quantity);
    Cart updateItemQuantityInCart(long customerId, int productId, int newQuantity);
    Cart removeItemFromCart(long customerId, int productId);
    List<CartItem> getCartItems(long customerId);
    Cart clearCart(long customerId);
    double getTotal(long customerId);
    List<CartItem> getCartItemsByProductId(int id);
}
