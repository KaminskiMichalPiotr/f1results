import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DriverModalService {

  constructor() {
  }

  private _modalOpen = new BehaviorSubject<boolean>(false);

  get modalOpen(): BehaviorSubject<boolean> {
    return this._modalOpen;
  }

  private _modalReset = new BehaviorSubject<boolean>(false);

  get modalReset(): BehaviorSubject<boolean> {
    return this._modalReset;
  }


}
