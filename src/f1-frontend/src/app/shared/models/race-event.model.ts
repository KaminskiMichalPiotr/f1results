import {Identity} from "./identity.model";
import {emptyLocation, Location} from "./location.model";

export interface RaceEvent extends Identity {

  location: Location;
  dateOfRace: string;
  index: number | null;

}

export function emptyRaceEvent(): RaceEvent {
  return {id: null, location: emptyLocation(), dateOfRace: '', index: null};
}
