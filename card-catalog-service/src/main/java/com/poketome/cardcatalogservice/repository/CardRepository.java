package com.poketome.cardcatalogservice.repository;

import com.poketome.cardcatalogservice.model.PokemonCard;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CardRepository {

    private final DynamoDbTable<PokemonCard> cardTable;

    // Inject the enhanced client we configured earlier
    public CardRepository(DynamoDbEnhancedClient enhancedClient) {
        // Map the Java class to a DynamoDB table named "PokemonCards"
        this.cardTable = enhancedClient.table("PokemonCards", TableSchema.fromBean(PokemonCard.class));
    }

    // 1. Save or Update a Card
    public void save(PokemonCard card) {
        cardTable.putItem(card);
    }

    // 2. Find a Card by its Partition Key (cardId)
    public PokemonCard findById(String cardId) {
        Key key = Key.builder().partitionValue(cardId).build();
        return cardTable.getItem(key);
    }

    // 3. Scan all cards in the table (useful for filtering/getting all items
    // locally)
    public List<PokemonCard> findAll() {
        List<PokemonCard> cards = new ArrayList<>();
        cardTable.scan().items().forEach(cards::add);
        return cards;
    }
}