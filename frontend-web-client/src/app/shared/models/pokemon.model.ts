// 1. The Contract for a single Pokemon Card
export interface PokemonCard {                  //We are creating a reusable blueprint named PokemonCard and unlocking it (export) so the rest of the app can use it.
  id: string;
  name: string;
  hp?: string; // the question mark means this is optional (Not all pokemon cards are actual pokemon)
  images: {
    small: string;
    large: string;
  };
}

// 2. The contract for the entire API delivery box
export interface PokemonApiResponse {
  data: PokemonCard[];
}
