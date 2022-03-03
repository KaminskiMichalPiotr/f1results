import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PATH} from "../../shared/variables";
import {Location} from "../../shared/models/location.model";
import {CrudService} from "./crud.service";

@Injectable({
  providedIn: 'root'
})
export class LocationService extends CrudService<Location> {

  constructor(http: HttpClient) {
    super(PATH + "location", http)
  }

}
