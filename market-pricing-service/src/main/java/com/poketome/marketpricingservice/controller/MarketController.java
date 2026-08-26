package com.poketome.marketpricingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.poketome.marketpricingservice.model.CardPriceRecord;
import com.poketome.marketpricingservice.service.MarketPricingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketPricingService marketPricingService;

    public MarketController(MarketPricingService marketPricingService) {
        this.marketPricingService = marketPricingService;
    }

    @GetMapping("/stats")
    public Map<String, Object> getMarketStats() {
        // In the future, this will query AWS AuroraDB or DynamoDB.
        // For now, we return our live placeholder data.
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTrackedCards", "14,250+");
        stats.put("avgMarketGrowth", "+12.4%");
        stats.put("trendingCard", "Charizard VMAX (Secret) - Live from Spring Boot!");

        return stats;
    }

    /* GET */
    @GetMapping("/card/{cardId}")
    public ResponseEntity<CardPriceRecord> getCardPricingRecord(@PathVariable String cardId) {
        CardPriceRecord cardPriceRecord = marketPricingService.getCardPricing(cardId);

        if (cardPriceRecord == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cardPriceRecord);
    }

    /* POST */
    @PostMapping("/card")
    public ResponseEntity<String> upserCardPricingRecord(@RequestBody CardPriceRecord cardPriceRecord) {
        marketPricingService.saveCardPricing(cardPriceRecord);
        return ResponseEntity.ok("Card pricing telemetry successfully saved");
    }
}