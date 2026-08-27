package com.poketome.cardcatalogservice.controller;

import com.poketome.cardcatalogservice.model.PokemonCard;
import com.poketome.cardcatalogservice.model.PokemonSet;
import com.poketome.cardcatalogservice.repository.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

        private final CardRepository cardRepository;

        // Inject the repository via constructor
        public CatalogController(CardRepository cardRepository) {
                this.cardRepository = cardRepository;
        }

        // Temporary set store until we build SetRepository
        private final List<PokemonSet> setsStore = new ArrayList<>();

        @GetMapping("/sets")
        public ResponseEntity<List<PokemonSet>> getAllSets() {
                return ResponseEntity.ok(setsStore);
        }

        @PostMapping("/sets")
        public ResponseEntity<String> saveSet(@RequestBody PokemonSet newSet) {
                setsStore.add(newSet);
                return ResponseEntity.ok("Pokemon set successfully saved to catalog");
        }

        // --- CARDS ENDPOINTS (Now hitting DynamoDB!) ---

        @GetMapping("/sets/{setId}/cards")
        public ResponseEntity<List<PokemonCard>> getCardsBySetId(@PathVariable String setId) {
                List<PokemonCard> allCards = cardRepository.findAll();
                List<PokemonCard> filteredCards = allCards.stream()
                                .filter(c -> c.getSetId() != null && c.getSetId().equalsIgnoreCase(setId))
                                .toList();
                return ResponseEntity.ok(filteredCards);
        }

        @GetMapping("/cards/{cardId}")
        public ResponseEntity<PokemonCard> getCardById(@PathVariable String cardId) {
                PokemonCard card = cardRepository.findById(cardId);
                if (card == null) {
                        return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(card);
        }

        @PostMapping("/card")
        public ResponseEntity<String> saveCard(@RequestBody PokemonCard newCard) {
                cardRepository.save(newCard);
                return ResponseEntity.ok("Pokemon card successfully saved to DynamoDB!");
        }
}