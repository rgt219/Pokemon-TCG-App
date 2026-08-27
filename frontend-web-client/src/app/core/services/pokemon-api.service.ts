// 2. pokemon-api.service.ts - The HTTP Service Layer targeting the API Gateway
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PokemonCard } from '../../shared/models/catalog.model';
import { CardPriceRecord } from '../../shared/models/market-pricing.model';

@Injectable({
  providedIn: 'root'
})
export class PokemonApiService {
  // Pointing directly to your API Gateway on port 8080
  private gatewayUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getCardCatalogDetails(cardId: string): Observable<PokemonCard> {
    return this.http.get<PokemonCard>(`${this.gatewayUrl}/api/catalog/cards/${cardId}`);
  }

  getCardPricing(cardId: string): Observable<CardPriceRecord> {
    return this.http.get<CardPriceRecord>(`${this.gatewayUrl}/api/market/card/${cardId}`);
  }
}
