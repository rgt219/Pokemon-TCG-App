package com.poketome.marketpricingservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.model.ResourceInUseException;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import java.net.URI;

@Configuration
public class DynamoDbConfig {

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .endpointOverride(URI.create("http://localhost:8000"))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("dummykey", "dummysecret")))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    /**
     * Automatically creates the card_market_pricing table on startup if it doesn't
     * exist
     */
    @Bean
    public CommandLineRunner initializeDatabase(DynamoDbClient dynamoDbClient) {
        return args -> {
            try {
                CreateTableRequest request = CreateTableRequest.builder()
                        .tableName("card_market_pricing")
                        .keySchema(KeySchemaElement.builder()
                                .attributeName("cardId")
                                .keyType(KeyType.HASH) // Partition Key
                                .build())
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName("cardId")
                                .attributeType(ScalarAttributeType.S) // String type
                                .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                .readCapacityUnits(5L)
                                .writeCapacityUnits(5L)
                                .build())
                        .build();

                dynamoDbClient.createTable(request);
                System.out.println(">> Successfully created local DynamoDB table: card_market_pricing");
            } catch (ResourceInUseException e) {
                System.out.println(">> Local DynamoDB table 'card_market_pricing' already exists. Skipping creation.");
            }
        };
    }
}