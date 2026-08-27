package com.poketome.marketpricingservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import java.util.List;

@Data
@DynamoDbBean
public class CardVariant {
    private String name; // e.g., "normal", "reverseHolofoil"
    private List<MarketplaceLink> marketplaces;
    private List<CardVariantPrice> prices;
}