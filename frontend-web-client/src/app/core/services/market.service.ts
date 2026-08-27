import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { CardPriceRecord } from "../../shared/models/market-pricing.model";

@Injectable({
  providedIn: 'root'
})
export class MarketService {
  private readonly apiUrlStats = 'http://localhost:8080/api/market/stats';
  private readonly apiUrl = 'http://localhost:8080/api/market';

  constructor(private readonly http: HttpClient) {}

  getMarketStats(): Observable<Record<string, any>> {
    return this.http.get<Record<string, any>>(this.apiUrlStats);
  }

  getCardPricing(cardId: string): Observable<CardPriceRecord> {
    return this.http.get<CardPriceRecord>(`${this.apiUrl}/card/${cardId}`);
  }
}
