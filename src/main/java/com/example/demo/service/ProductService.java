package com.example.demo.service;


import com.example.demo.domain.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    Product findProductByProductId(long productId);
    Product updateProduct(Product product);
    String removeProduct(Product product);
    Product addToCart(Product product);
}
