// CardAttack.java
package com.poketome.cardcatalogservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import java.util.List;

@Data
@DynamoDbBean
public class CardAttack {
    private String name;
    private List<String> cost;
    private Integer convertedEnergyCost;
    private String damage;
    private String text;
}