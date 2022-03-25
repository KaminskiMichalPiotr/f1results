import {Driver} from "./driver.model";

export interface SeasonResult {

  locationsTags: string[];
  results: DriverResultDto[];

}

export interface DriverResultDto {

  position: number;
  totalPoints: number;
  driver: Driver;
  positionInRace: Map<string, number>;

}

export interface DriverResultDto2 {

  position: number;
  totalPoints: number;
  driver: Driver;
  positionInRace: number[];

}


