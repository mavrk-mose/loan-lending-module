package io.credable.data.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan-requests")
public class Loan {

    //loan request model
    @Id
    @SequenceGenerator(
        name = "customer_id_sequence",
        sequenceName = "customer_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "customer_id_sequence"
        )
    private Integer Id;
    private String customer_number;
    private Integer amount;

    //constructors
    public Loan(Integer id, 
                String customer_number, 
                Integer amount) {
        this.Id = id;
        this.customer_number = customer_number;
        this.amount = amount;
    }

    public Loan() {
    }

    //getters and setters
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

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
