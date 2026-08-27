// CardAbility.java
package com.poketome.cardcatalogservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class CardAbility {
    private String name;
    private String text;
    private String type;
}