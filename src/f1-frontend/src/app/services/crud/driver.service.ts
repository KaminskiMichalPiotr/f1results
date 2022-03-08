import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Driver} from "../../shared/models/driver.model";
import {PATH} from "../../shared/variables";
import {CrudService} from "./crud.service";

@Injectable({
  providedIn: 'root'
})
export class DriverService extends CrudService<Driver> {

  constructor(http: HttpClient) {
    super(PATH + 'driver', http)
  }

}
