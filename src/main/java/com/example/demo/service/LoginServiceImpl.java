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

        //Get all users from db into a list
        List<User> users = getAllusers();

        for (User u : users) {

            if (u.getUsername().equalsIgnoreCase(user.getUsername())
                    && u.getPassword().equals(user.getPassword())) {

                u.setCart(null);

                if (!u.getRoles().equalsIgnoreCase("admin")) {

                    //Standard role, in case something goes wrong
                    u.setRoles("customer");
                }

                //Update the user with new data, in this case, role
                setUser(u);

                //Return the user which will stop this function from continue
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

        //Save all user from db into a list
        List<User> users = getAllusers();

        boolean userExist = false;

        //Loop through every user in list, compare by name to find a match
        for (User u : users) {
            if ((user.getUsername().equalsIgnoreCase(u.getUsername()))) {

                //This name does exist and is taken
                //Update our bool and break the loop
                userExist = true;
                break;
            }
        }

        if (!userExist) {
            user.setRoles("customer");
            userRepository.save(user);
            return user;
        }

        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
