package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner{
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public DbInit(UserRepository userRepository, ProductRepository productRepository){
        this.userRepository = userRepository; 
        this.productRepository = productRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("a", "test1", "test"));
        userRepository.save(new User("b", "test2", "test"));
        userRepository.save(new User("c", "test3", "test"));
        userRepository.save(new User("d", "test4", "test"));

        User kenny = new User("kenny", "kenny", "kenny123");
        User johnny = new User("johnny", "johnny", "johnny123","admin");
        User vincent = new User("vincent", "vincent", "vincent123", "admin");

        List<User> users = Arrays.asList(kenny, johnny, vincent);

        //sparar alla users i db när programmt körs
        this.userRepository.saveAll(users);

        Product shoe1 = new Product("shoe1", "nike", "airforce", "nice shoe", 100);
        Product shoe2 = new Product("shoe2", "nike", "airforce", "very nice shoe", 100);
        Product shoe3 = new Product("shoe3", "nike", "airforce", "super nice shoe", 100);
        Product shoe4 = new Product("shoe4", "nike", "batman", "dope shoe", 500000);

        List<Product> shoes = Arrays.asList(shoe1, shoe2, shoe3, shoe4);

        //sparar alla shor när programmt körs
        this.productRepository.saveAll(shoes);
    }
}