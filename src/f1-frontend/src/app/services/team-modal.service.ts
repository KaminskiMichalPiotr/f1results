import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {emptyTeam, Team} from "../models/team.model";

@Injectable({
  providedIn: 'root'
})
export class TeamModalService {

  constructor() {
  }

  private _selectedTeam = new BehaviorSubject<Team>(emptyTeam());

  get selectedTeam(): BehaviorSubject<Team> {
    return this._selectedTeam;
  }
}
