import { Component, OnInit, signal } from '@angular/core';
import { MarketService } from '../../core/services/market.service';
import { MarketStats } from '../../shared/models/market-stats';

@Component({
  selector: 'app-market-pricing',
  standalone: true,
  templateUrl: './market-pricing.component.html',
  styleUrl: './market-pricing.component.scss'
})
export class MarketPricingComponent implements OnInit {
  // We initialize an empty Signal that expects our exact Interface shape
  marketStats = signal<MarketStats | undefined>(undefined);

  constructor(private readonly marketService: MarketService) {}

  ngOnInit(): void {
    this.marketService.getMarketStats().subscribe({
      next: (data) => {
        // We cast the generic JSON to our strict MarketStats contract
        this.marketStats.set(data as MarketStats);
      },
      error: (err) => console.error('API failed', err)
    });
  }
}
