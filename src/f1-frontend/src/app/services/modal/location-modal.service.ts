import {Injectable} from '@angular/core';
import {emptyLocation, Location} from "../../shared/models/location.model";
import {ModalService} from "./modal.service";

@Injectable({
  providedIn: 'root'
})
export class LocationModalService extends ModalService<Location> {

  constructor() {
    super(emptyLocation());
  }

}
