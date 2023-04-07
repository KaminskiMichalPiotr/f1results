import {Component, OnInit} from '@angular/core';
import {DriverResultDto, SeasonResult} from "../../../shared/models/season-result.model";
import {SeasonService} from "../../../services/crud/season.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-season-result',
  templateUrl: './season-result.component.html',
  styleUrls: ['./season-result.component.css']
})
export class SeasonResultComponent implements OnInit {

  seasonResult!: SeasonResult;
  locationsTag: string[] = []
  driverResults: DriverResultDto[] = [];
  seasonSelector: number[] = [];
  season = new FormControl();

  constructor(private seasonService: SeasonService) {
  }

  ngOnInit(): void {
    this.seasonService.getSeasonsYears().subscribe(data => {
      this.seasonSelector = data
      this.seasonSelector.sort((a, b) => b - a);
      if (this.seasonSelector.length > 0) {
        this.seasonSelectEvent(this.seasonSelector[0])
        this.season.setValue(this.seasonSelector[0])
      }
    });
  }

  private static extractPos(pos: string) {
    return pos.substring(0, pos.indexOf("<"));
  }

  private static extractAdditionalInfo(info: string) {
    return info.substring(info.indexOf("<") + 1, info.indexOf(">"));
  }

  getPosition(positionInRace: Map<string, string>, index: number) {
    let number = positionInRace.get(this.seasonResult.locationsTags[index]);
    let pos;
    if (number) {
      pos = Number(SeasonResultComponent.extractPos(number));
      if (pos !== 404)
        return pos;
      else
        return 'DNF'
    }
    return '-';

  }

  getAdditionalInfo(positionInRace: Map<string, string>, index: number) {
    let number = positionInRace.get(this.seasonResult.locationsTags[index]);
    if (number)
      return SeasonResultComponent.extractAdditionalInfo(number);
    return '';
  }

  seasonSelectEvent(year: number) {
    this.seasonService.getSeasonResults(year).subscribe(data => {
      this.seasonResult = data
      this.locationsTag = this.seasonResult.locationsTags;
      this.driverResults = this.seasonResult.results;
      this.fixMap();
    })
  }


  sortPosition(a: DriverResultDto, b: DriverResultDto) {
    return b.position - a.position;
  }

  sortTotalPosition(a: DriverResultDto, b: DriverResultDto) {
    return b.totalPoints - a.totalPoints;
  }

  private fixMap() {
    this.driverResults.forEach(
      driverResult => {
        const result = Object.entries(driverResult.positionInRace);
        let map = new Map<string, string>();
        result.forEach(
          r => map.set(r[0], r[1])
        )
        driverResult.positionInRace = map;

      }
    )
  }
}
