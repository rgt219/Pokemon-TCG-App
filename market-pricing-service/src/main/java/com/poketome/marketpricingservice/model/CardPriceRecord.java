package com.poketome.marketpricingservice.model;

import java.util.List;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CardPriceRecord {

    private String cardId; // e.g., "me5-2" (Scrydex ID)
    private String cardName; // "Grubbin"
    private String setName; // "Pitch Black"
    private String cardNumber; // "002/084"
    private String rarity; // "Common"

    // Scrydex-compliant variant pricing structure
    private List<CardVariant> variants;

    private Long lastUpdatedEpoch;

    public CardPriceRecord() {
    }

    @DynamoDbPartitionKey
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<CardVariant> getVariants() {
        return variants;
    }

    public void setVariants(List<CardVariant> variants) {
        this.variants = variants;
    }

    public Long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(Long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }
}