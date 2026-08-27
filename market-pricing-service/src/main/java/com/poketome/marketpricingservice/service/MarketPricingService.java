package com.poketome.marketpricingservice.service;

import org.springframework.stereotype.Service;

import com.poketome.marketpricingservice.model.CardPriceRecord;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class MarketPricingService {

    private final DynamoDbTable<CardPriceRecord> cardTable;

    public MarketPricingService(DynamoDbEnhancedClient enhancedClient) {
        // Use fromBean() since CardPriceRecord has the @DynamoDbBean annotation
        this.cardTable = enhancedClient.table("CardPrices", TableSchema.fromBean(CardPriceRecord.class));
    }

    /**
     * Fetch a card's pricing telemetry by its Partition Key (O(1) read complexity)
     */
    public CardPriceRecord getCardPricing(String cardId) {
        return cardTable.getItem(Key.builder().partitionValue(cardId).build());
    }

    public void saveCardPricing(CardPriceRecord cardPriceRecord) {
        cardTable.putItem(cardPriceRecord);
    }
}