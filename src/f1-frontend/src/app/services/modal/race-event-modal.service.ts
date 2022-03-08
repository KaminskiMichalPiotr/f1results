import {Injectable} from '@angular/core';
import {ModalService} from "./modal.service";
import {emptyRaceEvent, RaceEvent} from "../../shared/models/race-event.model";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RaceEventModalService extends ModalService<RaceEvent> {

  public chosenSeason = new BehaviorSubject<number>(-1);

  constructor() {
    super(emptyRaceEvent())
  }
}
