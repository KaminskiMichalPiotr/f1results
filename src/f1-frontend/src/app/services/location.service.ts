import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {PATH} from "../shared/Variables";
import {Location} from "../models/location.model";

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  url = PATH + "location";

  constructor(private http: HttpClient) {
  }

  getLocations(): Observable<Location[]> {
    return this.http.get<Location[]>(this.url);
  }

  saveLocation(locationToSave: Location): Observable<Location> {
    return this.http.post<Location>(this.url, locationToSave);
  }
}
