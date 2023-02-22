package io.credable.data.model;

public class Loan {

    //loan request model
    private String customer_number;
    private Integer amount;

    //constructors
    public Loan(String customer_number, 
                Integer amount) {
        this.customer_number = customer_number;
        this.amount = amount;
    }

    public Loan() {
    }

    //getters and setters
    public String getCustomerNumber() {
        return customer_number;
    }

    public void setCustomerNumber(String customer_number) {
        this.customer_number = customer_number;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
