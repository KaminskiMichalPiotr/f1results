import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Driver, emptyDriver} from "../models/driver.model";

@Injectable({
  providedIn: 'root'
})
export class DriverModalService {

  constructor() {
  }

  private _modalOpen = new BehaviorSubject<boolean>(true);

  get modalOpen(): BehaviorSubject<boolean> {
    return this._modalOpen;
  }

  private _modalReset = new BehaviorSubject<boolean>(false);

  get modalReset(): BehaviorSubject<boolean> {
    return this._modalReset;
  }

  private _selectedDriver = new BehaviorSubject<Driver>(emptyDriver());

  get selectedDriver(): BehaviorSubject<Driver> {
    return this._selectedDriver;
  }

}
