import {Injectable} from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {emptyLocation, Location} from "../models/location.model";
import {NotificationModel} from "../models/notification.model";

@Injectable({
  providedIn: 'root'
})
export class LocationModalService {

  constructor() {
  }

  private _selectedLocation = new BehaviorSubject<Location>(emptyLocation());

  get selectedLocation(): BehaviorSubject<Location> {
    return this._selectedLocation;
  }

  private _notification = new Subject<NotificationModel>();

  get notification(): Subject<NotificationModel> {
    return this._notification;
  }

}
