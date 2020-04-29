package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
final class ProductController {

    private final ProductService productService;
    private final LoginService loginService;

    @Autowired
    public ProductController(final ProductService productService, final LoginService loginService) {
        this.productService = productService;
        this.loginService = loginService;
    }

    @GetMapping("products")
    ResponseEntity<List<Product>> productResult() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("admin")
    ResponseEntity<List<User>> adminResult() {
        return ResponseEntity.ok(loginService.getAllusersNoCart());
    }

}
