export interface SeasonResult {
  position: number;
  driver: string;
  races: Race[];
  totalPoints: number;
}

export interface Race {
  locationTag: string;
  position: number;
}


