import { Component, OnInit, signal } from '@angular/core';                                                    // 1. We import OnInit (the lifecycle hook) from the core toolbox
import { PokemonCardComponent } from '../../shared/components/pokemon-card/pokemon-card.component';
import { PokemonService } from '../../core/services/pokemon.service';                                 // 2. We import your specific service blueprint
import { PokemonCard } from '../../shared/models/catalog.model';

@Component({
  imports: [PokemonCardComponent],
  standalone: true,
  selector: 'app-home-page',
  styleUrl: './home-page.component.scss',
  templateUrl: './home-page.component.html',
})

export class HomePageComponent implements OnInit {                                   // By typing this, you are promising the Angular engine, "I guarantee I will include a setup function named ngOnInit in this file."

  // 4. Create an empty storage box for the live data
  pokemonList = signal<PokemonCard[]>([]);                                                           // a Signal is a reactive wrapper around your data. Instead of a silent cardboard box, think of a Signal as a high-tech vault with a loud alarm bell attached to it.

  // 5. The Hiring Process (Dependency Injection)
  constructor(private readonly pokemonService: PokemonService) {}                             // This is the Dependency Injection. When the Home Page is born, the constructor runs first. It yells, "I need the PokemonService tool!" Angular grabs it from the warehouse and securely hands it to the page, temporarily naming it pokemonService.

  // 6. The Launch Sequence
  ngOnInit(): void {
    this.pokemonService.getFeaturedCards().subscribe({
      next: (cards) => {
        this.pokemonList.set(cards); // 'cards' is already a PokemonCard[]
        console.log(cards);
      },
      error: (err) => {
        console.error('Failed to download cards!', err);
      }
    });
  }
}
