import { Routes } from '@angular/router';
import { HomePageComponent } from './features/home-page/home-page.component';
import { MarketPricingComponent } from './features/market-pricing/market-pricing.component';
import { SetExplorerComponent } from './features/set-explorer/set-explorer.component';
import { SetDetailComponent } from './features/set-detail/set-detail.component';
import { CardDetailComponent } from './features/card-detail/card-detail.component';
import { AuthComponent } from './features/auth/auth.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'market-pricing', component: MarketPricingComponent },
  { path: 'sets', component: SetExplorerComponent},
  { path: 'sets/:setId', component: SetDetailComponent},
  { path: 'cards/:cardId', component: CardDetailComponent }, // <-- New Layer 3 Route
  { path: 'auth', component: AuthComponent },

];
