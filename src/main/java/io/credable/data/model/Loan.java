package io.credable.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "loan-request")
public class Loan{
    //loan request model
    @Id
    @SequenceGenerator(
        name = "loan_id_sequence",
        sequenceName = "loan_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "loan_id_sequence"
        ) 
    private Long Id;
    private String customerNumber;
    private Integer amount;
}
