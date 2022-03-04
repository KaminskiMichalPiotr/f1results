import {Identity} from "./identity.model";

export interface RaceResult extends Identity {

}

export function emptyRaceResult() {
  return {id: null};
}
