import {Component, OnInit} from '@angular/core';
import {DriverService} from "../services/driver.service";
import {Driver} from "../models/driver.model";
import {DriverModalService} from "../services/driver-modal.service";
import {Team} from "../models/team.model";

@Component({
  selector: 'app-driver-edit',
  templateUrl: './driver-edit.component.html',
  styleUrls: ['./driver-edit.component.css'],
  providers: [DriverService, DriverModalService]
})
export class DriverEditComponent implements OnInit {
  isVisible: boolean = false;
  selectedDriver!: Driver;

  drivers: Driver[] = [];

  constructor(private driverService: DriverService, private driverModalService: DriverModalService) {
  }

  ngOnInit(): void {
    this.driverService.getDrivers().subscribe(data => {
      this.drivers = data
    })
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

  openModal(data: Driver) {
    this.selectedDriver = data;
    this.driverModalService.modalOpen.next(true);
  }

  openEmptyModal() {
    this.driverModalService.modalReset.next(true);
    this.driverModalService.modalOpen.next(true);
  }

  teamsName(teams: Team[]) {
    return teams.map(team => {
      return team.teamName
    }).toString();
  }
}
