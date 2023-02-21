package io.credable.data.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="KYC")
public class CustomerModel {
    
    //customer model
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

    //getters and setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CustomerModel() {
        
    }

    //model constructors
    public CustomerModel(
                    Long id,
                    Date createdAt, 
                    Date createdDate, 
                    String customerNumber, 
                    Date dob, 
                    String email, 
                    String firstName,
                    String gender, 
                    String idNumber, 
                    String idType, 
                    String lastName, 
                    String middleName, 
                    String mobile,
                    Double monthlyIncome, 
                    String status,
                    Date updatedAt) {
        this.Id = id;
        this.createdAt = createdAt;
        this.createdDate = createdDate;
        this.customerNumber = customerNumber;
        this.dob = dob;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.idNumber = idNumber;
        this.idType = idType;
        this.lastName = lastName;
        this.middleName = middleName;
        this.mobile = mobile;
        this.monthlyIncome = monthlyIncome;
        this.status = status;
        this.updatedAt = updatedAt;
    }

}