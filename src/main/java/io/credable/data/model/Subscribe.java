package io.credable.data.model;

public class Subscribe {

    //subscribe model
    private String customer_number;

    //constructors
    public Subscribe(String customer_number) {
        this.customer_number = customer_number;
    }

    public Subscribe() {
    }

    //getters and setters
    public String getCustomerNumber() {
        return customer_number;
    }

    public void setCustomerNumber(String customer_number) {
        this.customer_number = customer_number;
    }
 
}