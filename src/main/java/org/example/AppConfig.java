package org.example;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

public class AppConfig {

    @Bean
    public EcommerceApp ecommerceApp(){
        EcommerceApp ecommerceApp = new EcommerceApp();
//        ecommerceApp.addProducts(createProducts());
//        ecommerceApp.addCustomers(createCustomer());
//        ecommerceApp.addSellers(createSellers());
        return ecommerceApp;
    }

//    @Bean
//    public ArrayList<Customer> createCustomer(){
//        return new ArrayList<>(Arrays.asList(new Customer(1L, "CustomerName1", "Customer Address 1", "CustomerEmailAddress1", "customerpass1")));
//
//    }

//    @Bean
//    public ArrayList<Seller> createSellers(){
//        return new ArrayList<>(Arrays.asList(new Seller(100L, "SellerName1", "Seller Address 1", "SellerEmailAddress1", "sellerpass1")));
//
//    }

//    @Bean
//    public ArrayList<Product> createProducts(){
//        return new ArrayList<Product>(Arrays.asList
//                (new Product("product1", "productName1", 1000.0, "sellerID1", new Category("categoryName1", "categoryID1"))));
//    }


}
