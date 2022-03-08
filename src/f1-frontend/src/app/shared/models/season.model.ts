import {Identity} from "./identity.model";

export interface Season extends Identity {
  seasonYear: number;
}

export function emptySeason(): Season {
  return {id: null, seasonYear: 0};
}
