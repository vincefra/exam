package com.example.demo.service;

import com.example.demo.domain.CartProduct;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartProductRepository;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Inject
    private LoginServiceImpl loginServiceImpl;

    @Autowired
    CartProductRepository cartProductRepository;

    @Override
    public CartProduct saveOrUpdateCartProduct(CartProduct cp) {
        cartProductRepository.save(cp);
        return cp;
    }

    @Override
    public List<Product> getCartProductsForCustomer() {
        List<Product> cartProductsList = new ArrayList<>();
        for (CartProduct cp : cartProductRepository.findAll()) {
            if (!cp.getCart().isPurchased()) {
                if (cp.getCart().getUser().getUsername().equalsIgnoreCase(loginServiceImpl.getUser().getUsername())) {
                    cartProductsList.add(cp.getProduct());
                }
            }
        }
        return cartProductsList;
    }

    @Override
    public List<Product> getCartProductsForCustomer(long customerId) {
        List<Product> cartProductsList = new ArrayList<>();
        cartProductRepository.findAll().stream().filter((cp) -> (cp.getCart().isPurchased())).filter((cp) -> (cp.getCart().getUser().getId() == customerId)).map((cp) -> {
            return cp;
        }).forEachOrdered((cp) -> {
            cartProductsList.add(cp.getProduct());
        });
        return cartProductsList;
    }

    @Override
    public void removeCartProduct(Product cartProduct) {
        for (CartProduct cp : cartProductRepository.findAll()) {
            if (cp.getCart().getUser().getUsername().equalsIgnoreCase(loginServiceImpl.getUser().getUsername()) && cp.getProduct().getId() == cartProduct.getId()) {
                cartProductRepository.delete(cp);
                break;
            }
        }
    }

    @Override
    public double countTotalPrice(User user) {
        double totalPrice = 0;
        for (CartProduct cp : cartProductRepository.findAll()) {
            if (cp.getCart().getUser().getUsername().equalsIgnoreCase(user.getUsername()) && !cp.getCart().isPurchased()) {
                totalPrice += cp.getProduct().getPrice();
            }
        }
        System.out.println(totalPrice);
        return totalPrice;
    }

    @Override
    public boolean checkCartPremium(User user) {
        double totalPrice = 0;

        for (CartProduct cp : cartProductRepository.findAll()) {
            if (cp.getCart().getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                totalPrice += cp.getProduct().getPrice();
            }
        }

        return totalPrice > 500000;
    }
}
