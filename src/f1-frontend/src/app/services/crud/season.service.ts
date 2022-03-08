import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Season} from "../../shared/models/season.model";
import {HttpClient} from "@angular/common/http";
import {PATH} from "../../shared/variables";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SeasonService extends CrudService<Season> {

  constructor(http: HttpClient) {
    super(PATH + "season", http)
  }

  public getSeasonsYears(): Observable<number[]> {
    return this.http.get<number[]>(`${this.url}/seasonsYears`)
  }

}
