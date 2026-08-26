import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MarketPricingComponent } from './market-pricing.component';

describe('MarketPricingComponent', () => {
  let component: MarketPricingComponent;
  let fixture: ComponentFixture<MarketPricingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MarketPricingComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(MarketPricingComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
