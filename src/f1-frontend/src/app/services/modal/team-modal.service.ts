import {Injectable} from '@angular/core';
import {emptyTeam, Team} from "../../shared/models/team.model";
import {ModalService} from "./modal.service";

@Injectable({
  providedIn: 'root'
})
export class TeamModalService extends ModalService<Team> {

  constructor() {
    super(emptyTeam());
  }

}
