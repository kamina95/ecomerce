package org.example;

import loginApp.User;

public class Customer extends User {


    public boolean userLogin(String customerName, String customerPass){
        if(this.getUserName() == customerName && this.getUserPass() == customerPass){
            System.out.println("Customer Login Success!!");
            return true;
        }
        else{
            System.out.println("Incorrect customer login details !!!...Try again");
        }
        return false;
    }

    public Customer( String customerName, String customerAddress, String customerEmailAddress, String customerPass){
//        this.setUserId(customerId);
        this.setUserName(customerName);
        this.setUserAddress(customerAddress);
        this.setUserEmailAddress(customerEmailAddress);
        this.setUserPass(customerPass);
    }
}
