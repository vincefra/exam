/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;


import com.example.demo.domain.Product;
import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    Product findProductByProductId(long productId);
    Product addToCart(Product product);
}
