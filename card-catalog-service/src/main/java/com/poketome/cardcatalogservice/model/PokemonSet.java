package com.poketome.cardcatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class PokemonSet {
    private String setId; // e.g., "me5"
    private String name; // e.g., "Pitch Black"
    private String series; // e.g., "Mega Evolution"
    private String code; // e.g., "PBL"
    private String releaseDate; // e.g., "2026/07/17"
    private String logoUrl;
    private String symbolUrl;
    private Integer totalCards; // 120 (including secret rares)
    private Integer printedTotal; // 84
    private String language; // e.g., "English"
    private Boolean isOnlineOnly;

    @DynamoDbPartitionKey
    public String getSetId() {
        return setId;
    }
}