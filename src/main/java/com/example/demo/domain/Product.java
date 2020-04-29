package com.example.demo.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Product{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@ManyToMany
    //private List<Cart> carts;
    private String productType; // available "snowboard, binding, boot"
    private String brand;  
    private String name;
    private String description;
    private double price;
    private double premiumPrice;

    public Product(String productType, String brand,
                    String name, String description, 
                    double price) {
        //this.carts = carts;
        this.productType = productType;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.premiumPrice = price * 0.9;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    public double getCustomerPrice(User2 u) {
        
        if(u.getStatus().equals("premium")) { return price * 0.9;}
        
        return price;
    }
*/
    public double getPrice() {
        
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPremiumPrice() {
        return premiumPrice;
    }

    public void setPremiumPrice(double price) {
        this.premiumPrice = price;
    }   
/*
    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
*/
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productType=" + productType + ", brand=" + brand + ", name=" + name + ","
                + " description=" + description + ", price=" + price + ", premiumPrice=" + premiumPrice + '}';
    }
    
    
}
