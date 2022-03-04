import {Injectable} from '@angular/core';
import {ModalService} from "./modal.service";
import {emptyRaceEvent, RaceEvent} from "../../shared/models/race-event.model";

@Injectable({
  providedIn: 'root'
})
export class RaceEventModalService extends ModalService<RaceEvent> {

  constructor() {
    super(emptyRaceEvent())
  }
}
