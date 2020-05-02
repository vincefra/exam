package com.example.demo.service;


import com.example.demo.domain.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    Product findProductByProductId(long productId);
    Product addToCart(Product product);
}
