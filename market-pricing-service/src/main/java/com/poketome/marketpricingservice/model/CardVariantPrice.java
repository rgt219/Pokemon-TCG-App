package com.poketome.marketpricingservice.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class CardVariantPrice {
    private String condition; // e.g., "NM", "LP"
    private String type; // e.g., "raw"
    private Double low;
    private Double market;
    private TrendWindow trends;

    public CardVariantPrice() {
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getMarket() {
        return market;
    }

    public void setMarket(Double market) {
        this.market = market;
    }

    public TrendWindow getTrends() {
        return trends;
    }

    public void setTrends(TrendWindow trends) {
        this.trends = trends;
    }
}