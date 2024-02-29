package org.example;

import loginApp.User;

public class Seller extends User {

    public boolean userLogin(String sellerName, String sellerPass){
        if(this.getUserName() == sellerName && this.getUserPass() == sellerPass){
            System.out.println("Seller Login Success!!");
            return true;
        }
        else{
            System.out.println("Incorrect seller login details !!!...Try again");
        }
        return false;
    }
    public Seller( String sellerName, String sellerAddress, String sellerEmailAddress, String sellerPass){
        this.setUserName(sellerName);
        this.setUserAddress(sellerAddress);
        this.setUserEmailAddress(sellerEmailAddress);
        this.setUserPass(sellerPass);
    }

    public void addProducts(){

    }
}
