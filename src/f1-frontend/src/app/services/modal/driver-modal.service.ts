import {Injectable} from '@angular/core';
import {Driver, emptyDriver} from "../../shared/models/driver.model";
import {ModalService} from "./modal.service";


@Injectable({
  providedIn: 'root'
})
export class DriverModalService extends ModalService<Driver> {

  constructor() {
    super(emptyDriver())
  }

}
