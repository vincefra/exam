package com.example.demo.service;

import com.example.demo.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Johnn
 */
@Transactional
public interface LoginService {
    
    
   List<User> getAllusers();
   List<User> getAllusersNoCart();
   User getLoginUser(User user);
   User registerUser(User user);
   User getUser();
    
}
