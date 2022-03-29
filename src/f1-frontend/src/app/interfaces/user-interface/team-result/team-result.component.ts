import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-team-result',
  templateUrl: './team-result.component.html',
  styleUrls: ['./team-result.component.css']
})
export class TeamResultComponent implements OnInit {
  dataa: any = [{kuna: 'kuna'}];

  constructor() {
  }

  ngOnInit(): void {
  }

}
