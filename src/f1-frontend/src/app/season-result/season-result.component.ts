import {Component, OnInit} from '@angular/core';
import {SeasonResult} from "./season.result";

@Component({
  selector: 'app-season-result',
  templateUrl: './season-result.component.html',
  styleUrls: ['./season-result.component.css']
})
export class SeasonResultComponent implements OnInit {

  dataSet: SeasonResult[] = [
    {
      position: 1,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 5}, {locationTag: 'HUN', position: 5}, {locationTag: 'AUS', position: 5}],
      totalPoints: 50
    },
    {
      position: 2,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5}],
      totalPoints: 33
    },
  ];

  constructor() {
  }

  ngOnInit(): void {
  }

}
