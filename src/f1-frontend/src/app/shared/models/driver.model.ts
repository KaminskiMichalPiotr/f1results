import {Team} from "./team.model";
import {Identity} from "./identity.model";

export interface Driver extends Identity {
  name: string;
  nationality: string;
  dateOfBirth: string;
  teams: Team[];
}

export function emptyDriver(): Driver {
  return {id: null, name: '', nationality: '', dateOfBirth: '', teams: []}
}
