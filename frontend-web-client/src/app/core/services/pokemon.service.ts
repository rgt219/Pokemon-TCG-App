import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PokemonApiResponse } from '../../shared/models/pokemon.model';

@Injectable({         // This sticky note tells Angular, "This file is a tool that can be injected (handed out) to any page that asks for it."
  providedIn: 'root'  // This means the tool is available globally across the entire app.
})
export class PokemonService {
  private readonly apiUrl = 'https://api.pokemontcg.io/v2/cards?pageSize=10';

  constructor(private readonly http: HttpClient) {} //A constructor is the setup routine that runs the millisecond this service is born.

  getFeaturedCards(): Observable<PokemonApiResponse> {     // An Observable is like a digital subscription box. It says, "I don't have the cards right now, but here is a tracking number. I promise to deliver the data whenever it finally arrives."
    return this.http.get<PokemonApiResponse>(this.apiUrl);
  }
}
