package io.credable.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "QueryResponse")
public class QueryResponse {
    //expected response type
    @Id
    @SequenceGenerator(
        name = "score_id_sequence",
        sequenceName = "score_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "score_id_sequence"
        )
    private Integer id;
    private String customerNumber;
    private Double Score;
    private Double limitAmount;
    private String exclusion;
    private String exclusionReason; 
}
