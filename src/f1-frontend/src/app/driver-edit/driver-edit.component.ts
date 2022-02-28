import {Component, OnInit} from '@angular/core';
import {DriverService} from "../services/driver.service";
import {Driver} from "../models/driver.model";
import {DriverModalService} from "../services/driver-modal.service";
import {Team} from "../models/team.model";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-driver-edit',
  templateUrl: './driver-edit.component.html',
  styleUrls: ['./driver-edit.component.css'],
  providers: [DriverService, DriverModalService]
})
export class DriverEditComponent implements OnInit {

  drivers: Driver[] = [];

  constructor(private driverService: DriverService, private driverModalService: DriverModalService,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.driverService.getDrivers().subscribe(data => {
      this.drivers = data
    })
  }

  openEditModal(data: Driver) {
    this.driverModalService.selectedDriver.next(data);
    this.router.navigate(['edit'], {relativeTo: this.route})
  }

  openAddModal() {
    this.router.navigate(['add'], {relativeTo: this.route})
  }

  teamsName(teams: Team[]) {
    return teams.map(team => {
      return team.teamName
    }).toString();
  }

}
