import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {RaceEvent} from "../../shared/models/race-event.model";
import {HttpClient} from "@angular/common/http";
import {PATH} from "../../shared/variables";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RaceEventService extends CrudService<RaceEvent> {

  constructor(http: HttpClient) {
    super(PATH + "race-event", http)
  }

  public getRaceEventsByYear(year: number): Observable<RaceEvent[]> {
    return this.http.get<RaceEvent[]>(`${this.url}/by-season/${year}`)
  }

  public createRaceEvent(year: number, raceEvent: RaceEvent): Observable<RaceEvent> {
    return this.http.post<RaceEvent>(`${this.url}/${year}`, raceEvent);
  }

}
