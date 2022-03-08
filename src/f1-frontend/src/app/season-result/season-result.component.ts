import {Component, OnInit} from '@angular/core';
import {SeasonResultModel} from "./season-result.model";

@Component({
  selector: 'app-season-result',
  templateUrl: './season-result.component.html',
  styleUrls: ['./season-result.component.css']
})
export class SeasonResultComponent implements OnInit {

  dataSet: SeasonResultModel[] = [
    {
      position: 1,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 2,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    }, {
      position: 3,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 4,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    }, {
      position: 5,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 6,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    }, {
      position: 7,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 8,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    }, {
      position: 9,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 10,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    },
    {
      position: 11,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 12,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    },
    {
      position: 13,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 14,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    },
    {
      position: 15,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 16,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    },
    {
      position: 17,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 18,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    },
    {
      position: 19,
      driver: 'Charles Leclerc',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},],
      totalPoints: 50
    },
    {
      position: 20,
      driver: 'Max Verstappen',
      races: [{locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
        {locationTag: 'SAU', position: 8}, {locationTag: 'HUN', position: 7}, {locationTag: 'AUS', position: 5},
      ],
      totalPoints: 33
    },
  ];

  constructor() {
  }

  ngOnInit(): void {
  }

}
