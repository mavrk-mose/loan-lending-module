package io.credable.data.model;

public class QueryResponse {

    //expected response type
    private Integer id;
    private String customer_number;
    private Integer Score;
    private Long limitAmount;
    private String exclusion;
    private String exclusionReason;
    private String errorMessage;
    
    //getters and setters 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Long getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Long limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getExclusion() {
        return exclusion;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public String getExclusionReason() {
        return exclusionReason;
    }

    public void setExclusionReason(String exclusionReason) {
        this.exclusionReason = exclusionReason;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    //constructors
    public QueryResponse(Integer id, 
                         String customer_number, 
                         Integer score, 
                         Long limitAmount, 
                         String exclusion,
                         String exclusionReason,
                         String errorMessage) {
        this.id = id;
        this.customer_number = customer_number;
        Score = score;
        this.limitAmount = limitAmount;
        this.exclusion = exclusion;
        this.exclusionReason = exclusionReason;
        this.errorMessage = errorMessage;
    }
    
}
