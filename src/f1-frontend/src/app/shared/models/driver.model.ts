import {Team} from "./team.model";
import {Identity} from "./identity.model";

export interface Driver extends Identity {
  driver: string;
  nationality: string;
  dateOfBirth: string;
  teams: Team[];
}

export function emptyDriver(): Driver {
  return {id: null, driver: '', nationality: '', dateOfBirth: '', teams: []}
}
