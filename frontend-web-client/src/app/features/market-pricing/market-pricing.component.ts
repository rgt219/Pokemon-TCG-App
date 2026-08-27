import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MarketService } from '../../core/services/market.service';
import { MarketStats } from '../../shared/models/market-stats';
import { CardPriceRecord } from '../../shared/models/market-pricing.model'; // Adjust path as needed

@Component({
  selector: 'app-market-pricing',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './market-pricing.component.html',
  styleUrl: './market-pricing.component.scss'
})
export class MarketPricingComponent implements OnInit {
  // Existing Signal for stats[cite: 2]
  marketStats = signal<MarketStats | undefined>(undefined);

  // New Signals for individual card pricing & error handling
  cardPricing = signal<CardPriceRecord | undefined>(undefined);
  errorMessage = signal<string>('');

  constructor(private readonly marketService: MarketService) {}

  ngOnInit(): void {
    // 1. Fetch general market stats[cite: 2]
    this.marketService.getMarketStats().subscribe({
      next: (data) => {
        this.marketStats.set(data as MarketStats);
      },
      error: (err) => console.error('Market stats API failed', err)
    });

    // 2. Test fetching our specific card telemetry (Mega Darkrai ex: "me5-116")
    this.fetchCardPricing('me5-116');
  }

  fetchCardPricing(cardId: string): void {
    this.marketService.getCardPricing(cardId).subscribe({
      next: (data) => {
        this.cardPricing.set(data);
      },
      error: (err) => {
        this.errorMessage.set('Could not load card pricing telemetry from backend.');
        console.error('Card pricing API failed', err);
      }
    });
  }
}
