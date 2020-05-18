package com.example.demo.repository;

import com.example.demo.domain.Product;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
