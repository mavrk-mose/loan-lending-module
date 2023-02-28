package io.credable.data.model;

// import org.springframework.data.annotation.Id;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
// @Entity
// @Table(name = "loan-request")
public class Loan {
    //loan request model
    // @Id
    private Long Id;
    private String customer_number;
    private Integer amount;
}
