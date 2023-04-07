import {Component, OnInit} from '@angular/core';
import {SeasonService} from "../../../services/crud/season.service";
import {SingleRowTeamResultDto, TeamResultDto} from "../../../shared/models/season-team-result.model";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-team-result',
  templateUrl: './team-result.component.html',
  styleUrls: ['./team-result.component.css']
})
export class TeamResultComponent implements OnInit {

  locationsTags: string[] = []
  converted: SingleRowTeamResultDto[] = [];
  seasonSelector: number[] = [];
  season = new FormControl();

  constructor(private seasonService: SeasonService) {
  }

  ngOnInit(): void {
    this.seasonService.getSeasonsYears().subscribe(data => {
      this.seasonSelector = data
      this.seasonSelector.sort((year1, year2) => year2 - year1);
      if (this.seasonSelector.length > 0) {
        this.seasonSelectEvent(this.seasonSelector[0])
        this.season.setValue(this.seasonSelector[0])
      }
    });
  }

  extractTeamName(result: SingleRowTeamResultDto) {
    return result.team.teamName;
  }

  convertToSingleRowData(dtos: TeamResultDto[]) {
    let rowData: SingleRowTeamResultDto[] = [];
    dtos.forEach(dto => {
      let mapForFirstDriver = this.getMapForFirstDriver(dto);
      let mapForSecondDriver = this.getMapForSecondDriver(dto);
      let firstRow: SingleRowTeamResultDto = {
        position: dto.position,
        totalPoints: dto.totalPoints,
        team: dto.team,
        positionInRace: mapForFirstDriver
      };
      let secondRow: SingleRowTeamResultDto = {
        position: dto.position,
        totalPoints: dto.totalPoints,
        team: dto.team,
        positionInRace: mapForSecondDriver
      };
      rowData.push(firstRow, secondRow);
    })
    this.converted = rowData;
    console.log(this.converted);
  }

  getRacePosForDriver(result: SingleRowTeamResultDto, tag: string) {
    let pos = result.positionInRace.get(tag);
    if (pos === 404 || pos == 0)
      return 'DNF';
    else
      return pos !== undefined ? pos : "DNF";
  }

  seasonSelectEvent(year: number) {
    this.seasonService.getSeasonTeamResults(year).subscribe(
      data => {
        this.locationsTags = data.locationsTags;
        this.convertToSingleRowData(data.results);
      }
    )
  }

  private getMapForFirstDriver(dto: TeamResultDto) {
    const result = Object.entries(dto.positionInRace);
    let map = new Map<string, number>();
    result.forEach(
      r => map.set(r[0], r[1].driverOnePosition)
    )
    return map;
  }

  private getMapForSecondDriver(dto: TeamResultDto) {
    const result = Object.entries(dto.positionInRace);
    let map = new Map<string, number>();
    result.forEach(
      r => map.set(r[0], r[1].driverTwoPosition)
    )
    return map;
  }
}
