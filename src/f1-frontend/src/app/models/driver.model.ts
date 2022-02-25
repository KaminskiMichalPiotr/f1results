import {Team} from "./team.model";

export interface Driver {
  id: number | null;
  driver: string;
  nationality: string;
  dateOfBirth: string;
  teams: Team[];
}
