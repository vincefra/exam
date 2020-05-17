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

        Product p1 = new Product("Car", "Toyota", "Prius", "White Shine", 178000);
        Product p2 = new Product("Car", "Honda", "Accord", "Aurora Green", 192000);
        Product p3 = new Product("Car", "BMW", "M5", "Polished Carbon", 450000);
        Product p4 = new Product("Car", "Audi", "S5", "Black Diamond", 500000);
        Product p5 = new Product("Car", "Audi", "A5", "Red Carbon", 350000);
        Product p6 = new Product("Car", "Opel", "Mazda", "Silver", 250000);
        
        Product p7 = new Product("Car", "Volvo", "V90", "Silver", 450000);
        Product p8 = new Product("Car", "Volvo", "V70", "Black", 325000);
        Product p9 = new Product("Car", "Volkswagen", "Passat", "White", 280000);
        Product p10 = new Product("Car", "Volkswagen", "Golf", "Silver", 250000);
        Product p11 = new Product("Car", "Volkswagen", "Golf Combi", "Nano Grey", 310000);
        Product p12 = new Product("Car", "Volkswagen", "Golf Sport", "Midnight Black", 350000);
        Product p13 = new Product("Car", "Volkswagen", "Passat RS", "Gun Metal Grey", 450000);
        
        Product p14 = new Product("Car", "Tesla", "Model S", "Awesome Blue", 980000);
        Product p15 = new Product("Car", "Volkswagen", "Model 3", "Electric White", 650000);
        
        

        List<Product> products = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8,
                p9, p10, p11, p12, p13, p14, p15);

        this.productRepository.saveAll(products);
        //Save all products (arrayList) into productRepo
    }
}