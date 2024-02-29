package org.example;

import user.product.Product;

import java.util.ArrayList;

public class EcommerceApp {
    private ArrayList<Product> availableProducts = new ArrayList<>();

    private ArrayList<Seller> sellers = new ArrayList<>();

    private ArrayList<Customer> customers = new ArrayList<>();

    public void addProducts(ArrayList<Product> productList){
        this.availableProducts.addAll(productList);
    }

    public void addCustomers(ArrayList<Customer> customerList){
        this.customers.addAll(customerList);
    }

    public void addSellers(ArrayList<Seller> sellerList){
        this.sellers.addAll(sellerList);
    }

    public void displayProducts(){
        for(Product p: availableProducts){
            System.out.println(p);
        }
    }
}
