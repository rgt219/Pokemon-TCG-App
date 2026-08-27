import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CatalogService } from '../../core/services/catalog.service';
import { PokemonCard } from '../../shared/models/catalog.model';

@Component({
  selector: 'app-set-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './set-detail.component.html',
  styleUrl: './set-detail.component.scss'
})
export class SetDetailComponent implements OnInit {
  cards = signal<PokemonCard[]>([]);
  currentSetId = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private catalogService: CatalogService
  ) {}

  ngOnInit(): void {
    // Extract set ID from route parameters (e.g., /sets/me5)
    this.currentSetId = this.route.snapshot.paramMap.get('setId') || 'me5';

    this.catalogService.getCardsForSet(this.currentSetId).subscribe({
      next: (data) => this.cards.set(data),
      error: (err) => console.error('Failed to load card grid', err)
    });
  }

  onSelectCard(cardId: string): void {
    this.router.navigate(['/cards', cardId]);
  }
}
