import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {NotificationModel} from "../shared/models/notification.model";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() {
  }

  private _notification = new Subject<NotificationModel>();

  get notification(): Subject<NotificationModel> {
    return this._notification;
  }

}
