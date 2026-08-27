package com.poketome.marketpricingservice.repository;

import com.poketome.marketpricingservice.model.CardPriceRecord;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class PricingRepository {

    private final DynamoDbTable<CardPriceRecord> priceTable;

    public PricingRepository(DynamoDbEnhancedClient enhancedClient) {
        // Maps to a dedicated pricing table, keeping it completely separate from
        // catalog cards
        this.priceTable = enhancedClient.table("CardPrices", TableSchema.fromBean(CardPriceRecord.class));
    }

    public void save(CardPriceRecord priceRecord) {
        priceTable.putItem(priceRecord);
    }

    public CardPriceRecord findById(String cardId) {
        Key key = Key.builder().partitionValue(cardId).build();
        return priceTable.getItem(key);
    }
}