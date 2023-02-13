package io.credable.data.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="customer-number")
public class Subscribe {

    //subscribe model
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

    //constructors
    public Subscribe(String customer_number) {
        this.customer_number = customer_number;
    }
    public Subscribe(Integer id) {
        this.Id = id;
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
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
 
}