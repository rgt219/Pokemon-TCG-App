// CardTypeEffect.java (For Weaknesses & Resistances)
package com.poketome.cardcatalogservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class CardTypeEffect {
    private String type; // e.g., "Fire"
    private String value; // e.g., "×2" or "-30"
}