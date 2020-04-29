package com.example.demo.service;

import com.example.demo.domain.Cart;



public interface CartService {
    Cart saveOrUpdate(Cart cart);
    Cart getCartByUsername(String username);
    Cart cartCompleted();
    //int countProductIntCart();
}