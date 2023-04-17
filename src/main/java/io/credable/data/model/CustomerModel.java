package io.credable.data.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="kyc")
public class CustomerModel {
    //customer model
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String customerNumber;
    private Date createdAt;
    private Date createdDate;
    private Date updatedAt;
    private Date dob;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String email;
    private String mobile;
    private String idNumber;
    private String idType;
    private Double monthlyIncome;
    private String status;
}