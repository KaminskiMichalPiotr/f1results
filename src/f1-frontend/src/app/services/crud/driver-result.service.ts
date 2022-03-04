import {Injectable} from '@angular/core';
import {PATH} from "../../shared/variables";
import {HttpClient} from "@angular/common/http";
import {CrudService} from "./crud.service";
import {DriverResult} from "../../shared/models/driver-result.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DriverResultService extends CrudService<DriverResult> {

  constructor(http: HttpClient) {
    super(PATH + 'driver-result', http)
  }

  public getDriverResultByRaceId(raceId: number): Observable<DriverResult[]> {
    return this.http.get<DriverResult[]>(`${this.url}//driver-results-by-race/${raceId}`)
  }

}
