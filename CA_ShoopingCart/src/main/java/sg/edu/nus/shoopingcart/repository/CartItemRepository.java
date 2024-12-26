package sg.edu.nus.shoopingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import sg.edu.nus.shoopingcart.model.CartItem;

//Author: Siow Xiang Ying
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query("SELECT c FROM CartItem c WHERE c.cart.cartId = :cartId AND c.product.product_id = :productId and c.product.status=true")
    Optional<CartItem> findByCartIdAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);
    
    @Query("select c from CartItem c where c.product.product_id=:id and c.product.status=true")
    List<CartItem> findCartItemsByProductId(@Param("id")int id);
    
    @Query("SELECT c FROM CartItem c WHERE c.cart.cartId = :cartId")
    List<CartItem> findByCartId(@Param("cartId") int cartId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.cart.cartId = :cartId")
    void deleteByCartId(@Param("cartId") int cartId);


}

