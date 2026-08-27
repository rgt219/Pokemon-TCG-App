import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PokemonCard, PokemonSet } from '../../shared/models/catalog.model';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {
  private readonly apiUrl = 'http://localhost:8080/api/catalog'; // Routed via API Gateway

  constructor(private readonly http: HttpClient) {}

  getAllSets(): Observable<PokemonSet[]> {
    return this.http.get<PokemonSet[]>(`${this.apiUrl}/sets`);
  }

  getCardsForSet(setId: string): Observable<PokemonCard[]> {
    return this.http.get<PokemonCard[]>(`${this.apiUrl}/sets/${setId}/cards`);
  }

  getCardById(cardId: string): Observable<PokemonCard> {
    return this.http.get<PokemonCard>(`${this.apiUrl}/cards/${cardId}`)
  }
}
