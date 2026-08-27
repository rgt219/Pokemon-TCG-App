import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PokemonCard } from '../../shared/models/catalog.model';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
  // Pointing directly to your API Gateway routing to card-catalog-service
  private readonly apiUrl = 'http://localhost:8080/api/catalog';

  constructor(private readonly http: HttpClient) {}

  // Fetch featured or catalog cards from your local backend
  getFeaturedCards(): Observable<PokemonCard[]> {
    // For now, you can point this to a set endpoint or a general card endpoint
    return this.http.get<PokemonCard[]>(`${this.apiUrl}/sets/me5/cards`);
  }
}
