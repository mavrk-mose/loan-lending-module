package io.credable.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "loan-status")
public class Status {
    
    //status model
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
    private String Status;

    //constructors
    public Status(Integer id) {
        Id = id;
    }
    public Status(String status) {
        Status = status;
    }
    public Status() {
        
    }

    //getters and setters
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
