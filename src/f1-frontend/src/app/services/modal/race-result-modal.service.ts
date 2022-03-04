import {Injectable} from '@angular/core';
import {ModalService} from "./modal.service";
import {emptyRaceResult, RaceResult} from "../../shared/models/race-result.model";

@Injectable({
  providedIn: 'root'
})
export class RaceResultModalService extends ModalService<RaceResult> {

  constructor() {
    super(emptyRaceResult())
  }
}
