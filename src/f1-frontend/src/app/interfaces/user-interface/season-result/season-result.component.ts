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

  getPosition(positionInRace: Map<string, number>, index: number) {
    let number = positionInRace.get(this.seasonResult.locationsTags[index]);
    return number ? number : '-';

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
        let map = new Map<string, number>();
        result.forEach(
          r => map.set(r[0], r[1])
        )
        driverResult.positionInRace = map;

      }
    )
  }


}
