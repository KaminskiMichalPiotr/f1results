import {Identity} from "./identity.model";
import {Team} from "./team.model";
import {Driver} from "./driver.model";

export interface DriverResult extends Identity {

  driver?: Driver;
  position: number;
  team?: Team;
  points?: number;

}

export function emptyDriverResult(): DriverResult {
  return {id: null, position: 0};
}
