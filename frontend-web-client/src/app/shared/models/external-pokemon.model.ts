export interface ExternalPokemonCard {
  id: string; // External API uses 'id'[cite: 8]
  name: string;
  hp?: string;
  images: {
    small: string;
    large: string;
  };
}

export interface PokemonApiResponse {
  data: ExternalPokemonCard[];
}
