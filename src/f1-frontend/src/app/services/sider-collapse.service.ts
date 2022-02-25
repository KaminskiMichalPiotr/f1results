import {Injectable} from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SiderCollapseService {

  constructor() {

  }

  private _siderCollapse = new BehaviorSubject<boolean>(false);

  public get siderCollapse(): BehaviorSubject<boolean> {
    return this._siderCollapse;
  }
}
