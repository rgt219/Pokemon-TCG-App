import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CatalogService } from '../../core/services/catalog.service';
import { PokemonSet } from '../../shared/models/catalog.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-set-explorer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './set-explorer.component.html',
  styleUrl: './set-explorer.component.scss'
})
export class SetExplorerComponent implements OnInit {
  sets = signal<PokemonSet[]>([]);

  constructor(
    private readonly catalogService: CatalogService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.catalogService.getAllSets().subscribe({
      next: (data) => this.sets.set(data),
      error: (err) => console.error('Failed to load sets', err)
    });
}

  onSelectSet(setId: string): void {
    console.log('Navigating to Layer 2 for set:', setId);
    // 3. Trigger the routing transition to /sets/{setId}
    this.router.navigate(['/sets', setId]);
  }
}
