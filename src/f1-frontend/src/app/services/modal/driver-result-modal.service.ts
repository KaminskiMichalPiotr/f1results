import {Injectable} from '@angular/core';
import {ModalService} from "./modal.service";
import {DriverResult, emptyDriverResult} from "../../shared/models/driver-result.model";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DriverResultModalService extends ModalService<DriverResult> {

  public chosenRace!: BehaviorSubject<{ raceId: number, year: number }>;

  constructor() {
    super(emptyDriverResult())
  }
}
