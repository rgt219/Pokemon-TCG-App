// CardImage.java
package com.poketome.cardcatalogservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class CardImage {
    private String type; // "front"
    private String small;
    private String medium;
    private String large;
}