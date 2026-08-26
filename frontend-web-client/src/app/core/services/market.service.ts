import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class MarketService {
  private readonly apiUrl = 'http://localhost:8080/api/market/stats';

  constructor(private readonly http: HttpClient) {}

  getMarketStats(): Observable<Record<string, any>> {
    return this.http.get<Record<string, any>>(this.apiUrl);
  }
}
