package com.poketome.marketpricingservice.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class GradeMetric {
    private Double marketPrice;
    private Double priceChangeDelta;
    private String salesVolumeVelocity;

    public GradeMetric() {
    }

    public GradeMetric(Double marketPrice, Double priceChangeDelta, String salesVolumeVelocity) {
        this.marketPrice = marketPrice;
        this.priceChangeDelta = priceChangeDelta;
        this.salesVolumeVelocity = salesVolumeVelocity;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getPriceChangeDelta() {
        return priceChangeDelta;
    }

    public void setPriceChangeDelta(Double priceChangeDelta) {
        this.priceChangeDelta = priceChangeDelta;
    }

    public String getSalesVolumeVelocity() {
        return salesVolumeVelocity;
    }

    public void setSalesVolumeVelocity(String salesVolumeVelocity) {
        this.salesVolumeVelocity = salesVolumeVelocity;
    }
}
