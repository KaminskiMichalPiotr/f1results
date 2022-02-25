import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Driver} from "../models/driver.model";
import {Observable} from "rxjs";
import {PATH} from "../shared/Variables";

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private http: HttpClient) {
  }

  getDrivers(): Observable<Driver[]> {
    return this.http.get<Driver[]>("http://localhost:8080/driver/get");
  }

  saveDriver(driver: Driver): Observable<Driver> {
    return this.http.post<Driver>(PATH + 'driver', driver);
  }
}
