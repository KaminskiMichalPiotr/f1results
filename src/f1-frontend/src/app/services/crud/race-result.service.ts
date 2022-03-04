import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {RaceResult} from "../../shared/models/race-result.model";
import {PATH} from "../../shared/variables";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RaceResultService extends CrudService<RaceResult> {

  constructor(http: HttpClient) {
    super(`${PATH}race-result`, http)
  }
}
