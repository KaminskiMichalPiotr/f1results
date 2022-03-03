import {Identity} from "./identity.model";

export interface Location extends Identity {

  location: string;
  locationTag: string;
  country: string;

}

export function emptyLocation(): Location {
  return {id: null, location: '', locationTag: '', country: ''}
}
