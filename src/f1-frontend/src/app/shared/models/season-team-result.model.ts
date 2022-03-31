import {Team} from "./team.model";

export interface SeasonTeamResult {
  locationsTags: string[];
  results: TeamResultDto[];
}

export interface TeamResultDto {

  position: number;
  totalPoints: number;
  team: Team;
  positionInRace: Map<string, { driverOnePosition: number, driverTwoPosition: number }>;

}

export interface SingleRowTeamResultDto {

  position: number;
  totalPoints: number;
  team: Team;
  positionInRace: Map<string, number>;

}
