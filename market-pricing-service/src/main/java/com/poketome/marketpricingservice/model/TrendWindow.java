package com.poketome.marketpricingservice.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class TrendWindow {
    private PriceTrend days1;
    private PriceTrend days7;
    private PriceTrend days14;
    private PriceTrend days30;

    public TrendWindow() {
    }

    public PriceTrend getDays1() {
        return days1;
    }

    public void setDays1(PriceTrend days1) {
        this.days1 = days1;
    }

    public PriceTrend getDays7() {
        return days7;
    }

    public void setDays7(PriceTrend days7) {
        this.days7 = days7;
    }

    public PriceTrend getDays14() {
        return days14;
    }

    public void setDays14(PriceTrend days14) {
        this.days14 = days14;
    }

    public PriceTrend getDays30() {
        return days30;
    }

    public void setDays30(PriceTrend days30) {
        this.days30 = days30;
    }
}