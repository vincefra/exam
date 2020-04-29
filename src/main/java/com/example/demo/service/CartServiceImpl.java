    package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.repository.CartRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Inject
    private LoginServiceImpl loginServiceImpl;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartProductService cartProductService;

    @Override
    public Cart saveOrUpdate(Cart cart) {
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart getCartByUsername(String username) {
        List<Cart> carts = cartRepository.findAll();
        for(Cart c : carts)
            if(c.getUser().getUsername().equalsIgnoreCase(username) && !c.isPurchased())
                return c;
            
        return null;
    }
    
    @Override
    public Cart cartCompleted() {
        List<Cart> carts = cartRepository.findAll();
        for(Cart c : carts)
            if(!c.isPurchased()){
                if(c.getUser().getUsername().equalsIgnoreCase(loginServiceImpl.getUser().getUsername()))
                {
                    c.setTotalprice(cartProductService.countTotalPrice(loginServiceImpl.getUser()));
                    c.setPurchased(true);
                    saveOrUpdate(c);
                    if (cartProductService.checkCartPremium(loginServiceImpl.getUser()))
                        loginServiceImpl.getUser().setRoles("premium");

                    loginServiceImpl.getUser().setCart(new Cart());
                    return c;
                }
            }
        return null;
    }
}