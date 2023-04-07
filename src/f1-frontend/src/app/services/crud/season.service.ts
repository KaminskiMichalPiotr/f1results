import {Injectable} from '@angular/core';
import {CrudService} from "./crud.service";
import {Season} from "../../shared/models/season.model";
import {HttpClient} from "@angular/common/http";
import {PATH} from "../../shared/variables";
import {Observable} from "rxjs";
import {SeasonResult} from "../../shared/models/season-result.model";
import {SeasonTeamResult} from "../../shared/models/season-team-result.model";

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

  public getSeasonResults(seasonYear: number): Observable<SeasonResult> {
    return this.http.get<SeasonResult>(`${this.url}/seasonResult/${seasonYear}`)
  }

  public getSeasonTeamResults(seasonYear: number): Observable<SeasonTeamResult> {
    return this.http.get<SeasonTeamResult>(`${this.url}/seasonTeamResult/${seasonYear}`)
  }

}
