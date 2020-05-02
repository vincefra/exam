package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner{
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public DbInit(UserRepository userRepository, ProductRepository productRepository){
        this.userRepository = userRepository; 
        this.productRepository = productRepository;
        //Declare repo so we can call its functions
    }
    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("test", "test", "test123"));    
        //Add specific user as single in repo

        User admin = new User("admin", "admin", "admin123", "admin");
        User vincent = new User("vincent", "vincent", "vincent123", "admin");
        //Make new user object and add into ArrayList, use saveAll to save that list

        List<User> users = Arrays.asList(admin, vincent);
        
        this.userRepository.saveAll(users);
        //Save all users (arrayList) into userRepo

        Product p1 = new Product("Car", "Toyota", "Prius", "White Shine", 100);
        Product p2 = new Product("Car", "Honda", "Accord", "Aurora Green", 100);
        Product p3 = new Product("Car", "BMW", "M5", "Polished Carbon", 100);
        Product p4 = new Product("Car", "Audi", "A5", "Black Diamond", 500000);

        List<Product> products = Arrays.asList(p1, p2, p3, p4);

        this.productRepository.saveAll(products);
        //Save all products (arrayList) into productRepo
    }
}