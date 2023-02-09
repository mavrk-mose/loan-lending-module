package com.credable.model;

public class Subscribe {
    private Integer customer_id;

    public Subscribe(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Subscribe() {
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "Subscribe [customer_id=" + customer_id + "]";
    } 
      

}