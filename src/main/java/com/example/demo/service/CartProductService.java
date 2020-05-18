package com.example.demo.service;

import com.example.demo.domain.CartProduct;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import java.util.List;

public interface CartProductService {

    CartProduct saveOrUpdateCartProduct(CartProduct cp);

    List<Product> getCartProductsForCustomer();

    List<Product> getCartProductsForCustomer(long customerId);

    void removeCartProduct(Product cartProduct);

    double countTotalPrice(User user);

    boolean checkCartPremium(User user);
}
