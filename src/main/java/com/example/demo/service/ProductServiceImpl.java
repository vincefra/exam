package com.example.demo.service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartProduct;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartProductRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    //To be able to access functions from loginServiceImpl, we inject it
    @Inject
    private LoginServiceImpl loginServiceImpl;

    //To be able to access repo-functions for prod,cart, cartprod, user
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private UserRepository userRepository;

    //Save all product from repo into a list, return the list
    @Override
    public List<Product> getAllProducts() {
        List<Product> p = productRepository.findAll();
        return p;
    }

    //If product not null, use prodRepo and save the product, 
    //it will handle automatically and update old product by same id, else it inserts new product
    @Override
    public Product updateProduct(Product product) {
        if (product != null) {
            productRepository.save(product);
            return product;
        }

        return null;
    }

    @Override
    public String removeProduct(Product product) {

        if (product != null) {
            //If product not null, delete it from repo and return OK
            productRepository.delete(product);
            return "OK";
        }

        //Product is null, return NO
        return "NO";
    }

    @Override
    public Product findProductByProductId(long productId) {
        List<Product> products = getAllProducts();

        //Loop thru every product, compare by id and return when matched
        for (Product p : products) {
            if (p.getId() == productId) {
                return p;
            }
        }

        //Couldnt find, return null
        return null;
    }

    @Override
    public Product addToCart(Product product) {
        User user = loginServiceImpl.getUser();
        Cart cart = user.getCart();

        //kolla ifall cart inte är null, ifall cart är köpt? skapa ny cart
        if (cart == null) {
            cart = new Cart();
        } else if (cart.isPurchased()) {
            cart = new Cart();
        }

        //setta cart och user till varandra, dvs koppla ihop
        cart.setUser(user);
        loginServiceImpl.getUser().setCart(cart);

        //ny cartProduct
        CartProduct cp = new CartProduct(product, cart, 1, user.getRoles().equalsIgnoreCase("premium"));

        //spara cart
        cartRepository.save(cart);

        //spara cartProduct
        cartProductRepository.save(cp);

        //spara user -> cart
        userRepository.save(user);

        return product;
    }
}
