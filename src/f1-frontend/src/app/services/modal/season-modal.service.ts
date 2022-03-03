import {Injectable} from '@angular/core';
import {ModalService} from "./modal.service";
import {emptySeason, Season} from "../../shared/models/season.model";

@Injectable({
  providedIn: 'root'
})
export class SeasonModalService extends ModalService<Season> {

  constructor() {
    super(emptySeason())
  }

}
