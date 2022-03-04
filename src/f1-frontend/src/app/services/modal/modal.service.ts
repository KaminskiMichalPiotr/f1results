import {BehaviorSubject, Subject} from "rxjs";

export class ModalService<T> {

  protected constructor(private initialValue: T) {
  }

  private _refresh = new Subject<T>();

  get refresh(): Subject<T> {
    return this._refresh;
  }

  private _selected = new BehaviorSubject<T>(this.initialValue);

  get selected(): BehaviorSubject<T> {
    return this._selected;
  }

  private _delete = new Subject<T>();

  get delete(): Subject<T> {
    return this._delete;
  }

}
