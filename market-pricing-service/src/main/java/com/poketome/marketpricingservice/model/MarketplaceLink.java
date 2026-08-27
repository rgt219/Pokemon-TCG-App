package com.poketome.marketpricingservice.model;

import lombok.Data;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Data
@DynamoDbBean
public class MarketplaceLink {
    private String name; // e.g., "tcgplayer"
    private String productId;
    private String purchaseUrl;
}