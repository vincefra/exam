package com.example.demo.repository;

import com.example.demo.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

}
