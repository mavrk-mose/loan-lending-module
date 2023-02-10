package com.credable.model;

import org.hibernate.annotations.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(appliesTo = "customer-kyc")
public class Subscribe {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"   
    )
    
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