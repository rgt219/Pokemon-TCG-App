package com.poketome.marketpricingservice.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class RecentSale {
    private String saleDate;
    private String listingTitle;
    private Double purchasePrice;
    private String marketplace;
    private Boolean isShadowless;

    public RecentSale() {
      // TODO document why this constructor is empty
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }

    public Boolean getIsShadowless() {
        return isShadowless;
    }

    public void setIsShadowless(Boolean shadowless) {
        isShadowless = shadowless;
    }
}
