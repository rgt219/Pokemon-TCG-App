export interface CardAttack {
  name: string;
  cost: string[];
  convertedEnergyCost: number;
  damage?: string;
  text: string;
}

export interface CardAbility {
  name: string;
  text: string;
  type: string;
}

export interface CardLegality {
  format: string; // "Standard", "Expanded", "Unlimited"
  status: string; // "Legal", "Banned"
}

export interface CardTypeEffect {
  type: string; // e.g., "Fire"
  value: string; // e.g., "×2" or "-30"
}

export interface CardImage {
  type: string; // "front"
  small: string;
  medium: string;
  large: string;
}

export interface PokemonCard {
  cardId: string; // e.g., "me5-1"[cite: 22]
  setId: string; // e.g., "me5"[cite: 22]
  name: string; // e.g., "Tropius"[cite: 22]
  supertype?: string; // e.g., "Pokémon"[cite: 22]
  subtypes?: string[]; // e.g., ["Basic"][cite: 22]
  types?: string[]; // e.g., ["Grass"][cite: 22]
  hp?: string; // e.g., "110"[cite: 22]
  cardNumber: string; // e.g., "1"[cite: 22]
  printedNumber?: string; // e.g., "001/084"[cite: 22]
  rarity: string; // e.g., "Common"[cite: 22]
  rarityCode?: string; // e.g., "C"[cite: 22]
  artist?: string;
  regulationMark?: string; // e.g., "J"[cite: 22]
  flavorText?: string;
  evolvesFrom?: string[];
  abilities?: CardAbility[];
  attacks?: CardAttack[];
  weaknesses?: CardTypeEffect[];
  resistances?: CardTypeEffect[];
  retreatCost?: string[];
  convertedRetreatCost?: number;
  rules?: string[];
  nationalPokedexNumbers?: number[];
  legalities?: CardLegality[];
  images?: CardImage[];
}

export interface PokemonSet {
  setId: string; // e.g., "me5"[cite: 23]
  name: string; // e.g., "Pitch Black"[cite: 23]
  series?: string; // e.g., "Mega Evolution"[cite: 23]
  code?: string; // e.g., "PBL"[cite: 23]
  releaseDate: string; // e.g., "2026/07/17"[cite: 23]
  logoUrl: string;
  symbolUrl: string;
  totalCards: number; // 120[cite: 23]
  printedTotal?: number; // 84[cite: 23]
  language?: string; // e.g., "English"[cite: 23]
  isOnlineOnly?: boolean;
}
