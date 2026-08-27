// CardLegality.java
package com.poketome.cardcatalogservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class CardLegality {
    private String format; // "Standard", "Expanded", "Unlimited"
    private String status; // "Legal", "Banned"
}