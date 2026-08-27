import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CatalogService } from '../../core/services/catalog.service';
import { MarketService } from '../../core/services/market.service';
import { PokemonCard } from '../../shared/models/catalog.model';
import { CardPriceRecord } from '../../shared/models/market-pricing.model';

@Component({
  selector: 'app-card-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-detail.component.html',
  styleUrl: './card-detail.component.scss'
})
export class CardDetailComponent implements OnInit {
  cardMetadata = signal<PokemonCard | null>(null);
  cardPricing = signal<CardPriceRecord | null>(null);
  isLoadingPricing = signal<boolean>(true);
  isOpen = signal<boolean>(false);

  // Interactive Hover State for Price Chart
  hoveredPrice = signal<{ date: string; price: number; label: string } | null>(null);

  constructor(
    private readonly route: ActivatedRoute,
    private readonly catalogService: CatalogService,
    private readonly marketService: MarketService
  ) {}

  ngOnInit(): void {
    const cardId = this.route.snapshot.paramMap.get('cardId');
    if (cardId) {
      this.catalogService.getCardById(cardId).subscribe({
        next: (data) => {
          this.cardMetadata.set(data);
          setTimeout(() => this.isOpen.set(true), 600);
        },
        error: (err) => console.error('Failed to load catalog data', err)
      });

      this.marketService.getCardPricing(cardId).subscribe({
        next: (data) => {
          this.cardPricing.set(data);
          this.isLoadingPricing.set(false);
        },
        error: (err) => {
          console.error('Failed to load market pricing data', err);
          this.isLoadingPricing.set(false);
        }
      });
    }
  }

  // Interactive Chart Hover Handler simulating date/price points from trend windows
  onChartMouseMove(event: MouseEvent, variantName: string): void {
    const svgRect = (event.currentTarget as HTMLElement).getBoundingClientRect();
    const xPos = event.clientX - svgRect.left;
    const percentage = xPos / svgRect.width;

    // Simulate interactive time series data points across 30 days
    const mockDates = ['Aug 13, 2026', 'Aug 15, 2026', 'Aug 18, 2026', 'Aug 21, 2026', 'Aug 24, 2026', 'Aug 27, 2026'];
    const index = Math.min(Math.floor(percentage * mockDates.length), mockDates.length - 1);

    const pricing = this.cardPricing();
    const variant = pricing?.variants?.find(v => v.name === variantName) || pricing?.variants?.[0];
    const basePrice = variant?.prices?.[0]?.market || 0.15;
    const simulatedPrice = Number((basePrice + (index * 0.01) - 0.02).toFixed(2));

    this.hoveredPrice.set({
      date: mockDates[index],
      price: simulatedPrice,
      label: variantName || 'NM'
    });
  }

  onChartMouseLeave(): void {
    this.hoveredPrice.set(null);
  }
}
