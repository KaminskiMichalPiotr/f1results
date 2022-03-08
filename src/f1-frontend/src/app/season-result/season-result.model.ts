export interface SeasonResultModel {
  position: number;
  driver: string;
  races: Race[];
  totalPoints: number;
}

export interface Race {
  locationTag: string;
  position: number;
}


