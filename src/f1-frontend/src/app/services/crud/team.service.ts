import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PATH} from "../../shared/variables";
import {Team} from "../../shared/models/team.model";
import {CrudService} from "./crud.service";

@Injectable({
  providedIn: 'root'
})
export class TeamService extends CrudService<Team> {

  constructor(http: HttpClient) {
    super(PATH + "team", http)
  }

}
