package com.example.demo.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@ToString
@EqualsAndHashCode
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String name;
    private String username;
    private String password;
    private String roles;

    @OneToOne
    private Cart cart;

    // Empty constructor for JSON/JPA
    public User() {
        name = null;
        username = null;
        password = null;
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = "customer";
    }

    public User(String name, String username, String password, String roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        this.roles = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
