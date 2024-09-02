package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.CartItemRepository;
import com.ecom.repository.CartRepository;
import com.ecom.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.getUserByJwtToken(jwt);
        Product product = productService.getProductById(req.getProductId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getId() == product.getId()) {
                int quantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), quantity);
            }

            return null;
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setTotalPrice(req.getQuantity() * product.getPrice());
        CartItem savCartItem = cartItemRepository.save(cartItem);

        cart.getCartItems().add(savCartItem);
        return savCartItem;

    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * item.getProduct().getPrice());
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.getUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItem.get();
        cart.getCartItems().remove(item);

        cartItemRepository.delete(item);

        return cartRepository.save(cart);
    }

    @Override
    public Double calculateCartTotals(Cart cart) throws Exception {
        Double total = 0D;
        for (CartItem item : cart.getCartItems()) {
            total += item.getTotalPrice();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepository.findById(id);

        if (cart.isEmpty()) {
            throw new Exception("Cart not found with id: " + id);
        }
        return cart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        return cartRepository.findByCustomerId(userId);
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.getUserByJwtToken(jwt);
        Cart cart = findCartByUserId(user.getId());
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

}
