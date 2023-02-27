package io.credable.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QueryResponse {
    //expected response type
    private Integer id;
    private String customerNumber;
    private Double Score;
    private Double limitAmount;
    private String exclusion;
    private String exclusionReason; 
}
