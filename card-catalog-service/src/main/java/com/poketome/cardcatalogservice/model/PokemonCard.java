package com.poketome.cardcatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class PokemonCard {
    private String cardId; // e.g., "me5-1"
    private String setId; // e.g., "me5"
    private String name; // e.g., "Tropius"
    private String supertype; // e.g., "Pokémon", "Trainer", "Energy"
    private List<String> subtypes; // e.g., ["Basic"]
    private List<String> types; // e.g., ["Grass"]
    private String hp; // e.g., "110"
    private String cardNumber; // e.g., "1"
    private String printedNumber; // e.g., "001/084"
    private String rarity; // e.g., "Common"
    private String rarityCode; // e.g., "C"
    private String artist;
    private String regulationMark; // e.g., "J"
    private String flavorText;

    private List<String> evolvesFrom;
    private List<CardAbility> abilities;
    private List<CardAttack> attacks;
    private List<CardTypeEffect> weaknesses;
    private List<CardTypeEffect> resistances;
    private List<String> retreatCost;
    private Integer convertedRetreatCost;
    private List<String> rules;
    private List<Integer> nationalPokedexNumbers;
    private List<CardLegality> legalities;
    private List<CardImage> images;

    @DynamoDbPartitionKey
    public String getCardId() {
        return cardId;
    }

    public String getSetId() {
        return setId;
    }
}