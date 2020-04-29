package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
final class RegistrationController {

    private final LoginService loginService;

    @Autowired
    public RegistrationController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping()
    ResponseEntity<User> postResult(@RequestBody User user) {
        return ResponseEntity.ok(loginService.registerUser(user));
    }

}
