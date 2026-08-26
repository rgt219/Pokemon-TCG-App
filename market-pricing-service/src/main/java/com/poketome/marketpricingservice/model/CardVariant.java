package com.poketome.marketpricingservice.model;

import java.util.List;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class CardVariant {
    private String name; // e.g., "normal", "reverseHolofoil"
    private List<CardVariantPrice> prices;

    public CardVariant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CardVariantPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<CardVariantPrice> prices) {
        this.prices = prices;
    }
}