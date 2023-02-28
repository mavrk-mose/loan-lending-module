package io.credable.data.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
// @Entity
// @Table(name = "QueryResponse")
public class QueryResponse {
    //expected response type
    // @Id
    private Integer id;
    private String customerNumber;
    private Double Score;
    private Double limitAmount;
    private String exclusion;
    private String exclusionReason; 
}
