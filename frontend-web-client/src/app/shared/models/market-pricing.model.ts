export interface PriceTrend {
  priceChange: number;
  percentChange: number;
}

export interface TrendWindow {
  days1: PriceTrend;
  days7: PriceTrend;
  days14: PriceTrend;
  days30: PriceTrend;
}

export interface CardVariantPrice {
  condition: string;
  type: string;
  low: number;
  market: number;
  trends: TrendWindow;
}

export interface CardVariant {
  name: string;
  prices: CardVariantPrice[];
}

export interface CardPriceRecord {
  cardId: string;
  cardName: string;
  setName: string;
  cardNumber: string;
  variants: CardVariant[];
  rarity: string;
}


