import { Component, Input } from '@angular/core';

@Component({
  imports: [],
  standalone: true,
  selector: 'app-pokemon-card',
  styleUrl: './pokemon-card.component.scss',
  templateUrl: './pokemon-card.component.html',
})

export class PokemonCardComponent {
  @Input() cardName: string = 'MissingNo';
  @Input() imageUrl: string = '';
  @Input() hp?: string = 'N/A';
}
