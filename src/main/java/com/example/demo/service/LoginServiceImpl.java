package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private CartProductServiceImpl cartProductServiceImpl;
    
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private User user;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User getLoginUser(User user) {

        List<User> users = getAllusers();
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())
                    && u.getPassword().equals(user.getPassword())) 
            {
                u.setCart(null);
                
                if (!u.getRoles().equalsIgnoreCase("admin"))
                {
                    if (cartProductServiceImpl.checkCartPremium(u))
                        u.setRoles("premium");
                    else
                        u.setRoles("customer");
                }
                
                setUser(u);
                return u;
            }
        }
        return new User();
    }
    
    @Override
    public User getUser() {
        return this.user;
    }
    

    @Override
    public List<User> getAllusers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    
    
    @Override
    public List<User> getAllusersNoCart() {
        List<User> users = userRepository.findAll();
        users.forEach((u) -> {
            u.setCart(null);
        });
        
        return users;
    }

    @Override
    public User registerUser(User user) {
        
        List<User> users = getAllusers();
        // if-satsen ska tas bort när vi skapat databasen
        
        if(users.size() > 0){
        for (User u : users) {
            if (!(user.getUsername().equalsIgnoreCase(u.getUsername()))) {
                user.setRoles("customer");
                userRepository.save(user);
                return user;
                }
            }
        }
        // Detta ska vi ta bort när vi har skapat databasen
        else {
            user.setRoles("customer");
            userRepository.save(user);
            return user;
        }
        
        return new User();
    }


    public void setUser(User user) {
        this.user = user;
    }
    
    
}
