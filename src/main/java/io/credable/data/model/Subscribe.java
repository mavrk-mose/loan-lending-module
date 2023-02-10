package io.credable.data.model;

import org.hibernate.annotations.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(appliesTo="kyc")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customer_id") 
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