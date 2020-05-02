package com.example.demo.controller;

import com.example.demo.domain.Cart;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.CartProductService;
import com.example.demo.service.CartService;
import com.example.demo.service.CartServiceImpl;
import com.example.demo.service.LoginService;
import com.example.demo.service.LoginServiceImpl;
import com.example.demo.service.ProductService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
final class CartController {

    private final ProductService productService;
    private final CartProductService cartProductService;
    private final CartServiceImpl cartServiceImpl;
    
    @Autowired
    public CartController(final ProductService productService, final CartProductService cartProductService, final CartServiceImpl cartServiceImpl) {
        this.productService = productService;
        this.cartProductService = cartProductService;
        this.cartServiceImpl = cartServiceImpl;
    }

    @PostMapping("/add")
    ResponseEntity<Product> add(@RequestBody int productId) {
        Product product = productService.findProductByProductId(productId);
        
        System.out.println(product.getName());
        return ResponseEntity.ok(productService.addToCart(product));
    }
    
    @PostMapping("/remove")
    ResponseEntity<Product> remove(@RequestBody int productId) {
        Product product = productService.findProductByProductId(productId);
        cartProductService.removeCartProduct(product);
        
        System.out.println(product.getName());
        return ResponseEntity.ok(product);
    }
    
    @GetMapping("/buy")
    ResponseEntity<Cart> buy() {
        Cart c = cartServiceImpl.cartCompleted();
        Cart cc = new Cart();
        
        cc.setId(c.getId());
        cc.setPurchased(c.isPurchased());
        cc.setQuantity(c.getQuantity());
        cc.setTotalprice(c.getTotalprice());

        return ResponseEntity.ok(cc);
    }
    
    @GetMapping("/products")
    ResponseEntity<List<Product>> postResult() {
        return ResponseEntity.ok(cartProductService.getCartProductsForCustomer());
    }
    
    @PostMapping("/check")
    ResponseEntity<List<Product>> check(@RequestBody long customerId) {
      return ResponseEntity.ok(cartProductService.getCartProductsForCustomer(customerId));
    }
}
