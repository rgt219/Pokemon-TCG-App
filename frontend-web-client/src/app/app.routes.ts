import { Routes } from '@angular/router';
import { HomePageComponent } from './features/home-page/home-page.component';
import { MarketPricingComponent } from './features/market-pricing/market-pricing.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'market-pricing', component: MarketPricingComponent }
];
