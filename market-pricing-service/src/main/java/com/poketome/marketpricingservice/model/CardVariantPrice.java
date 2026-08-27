package com.poketome.marketpricingservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class CardVariantPrice {
    private String condition; // "NM", "LP", "MP", "HP", "DMG"
    private String type; // "raw", "graded"
    private String grade; // e.g., null, "10", "9.5"
    private String company; // e.g., null, "PSA", "BGS", "CGC"
    private Boolean isPerfect;
    private Boolean isSigned;
    private Boolean isError;

    private Double low;
    private Double mid;
    private Double high;
    private Double market;
    private String currency; // "USD"

    private TrendWindow trends;
}