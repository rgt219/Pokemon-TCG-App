import { Component, OnInit, signal } from '@angular/core';                                                    // 1. We import OnInit (the lifecycle hook) from the core toolbox
import { PokemonCardComponent } from '../../shared/components/pokemon-card/pokemon-card.component';
import { PokemonService } from '../../core/services/pokemon.service';                                 // 2. We import your specific service blueprint
import { PokemonCard } from '../../shared/models/pokemon.model';

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
  ngOnInit(): void {                                                                  // This stands for "Angular On Initialize." This is the launch sequence. It runs automatically right after the constructor finishes.
    this.pokemonService.getFeaturedCards().subscribe({                                // Remember how the service gives us a tracking number instead of instant data? subscribe is how we wait for the delivery truck.
      next: (response) => {                                                           // next: means "When the data finally arrives successfully, do this."
        // The API delivers the cards inside a digital folder called 'data'
        this.pokemonList.set(response.data);                                          // When you write this.pokemonList.set(response.data), you are securely locking the new Pokémon into the vault, which guarantees the alarm bell gets triggered for the HTML to hear.
        console.log(response.data);
      },
      error: (err) => {                                                               // error: means "If the internet is down or the API crashes, do this instead."
        console.error('Failed to download cards!', err);
      }
    });
  }
}
