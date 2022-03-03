import {Component, OnDestroy, OnInit} from '@angular/core';
import {DriverService} from "../services/crud/driver.service";
import {Driver} from "../shared/models/driver.model";
import {DriverModalService} from "../services/modal/driver-modal.service";
import {Team} from "../shared/models/team.model";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {ModalOpener} from "../services/modal/modal-opener";

@Component({
  selector: 'app-driver-edit',
  templateUrl: './driver-edit.component.html',
  styleUrls: ['./driver-edit.component.css'],
  providers: [DriverService, DriverModalService]
})
export class DriverEditComponent extends ModalOpener<Driver> implements OnInit, OnDestroy {

  subs: Subscription[] = []

  drivers: Driver[] = [];

  constructor(private driverService: DriverService, private driverModalService: DriverModalService,
              router: Router, route: ActivatedRoute) {
    super(driverModalService, router, route);
  }

  ngOnInit(): void {
    this.loadDrivers();
    this.driverChangeSubscription();
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  driverChangeSubscription() {
    this.subs.push(this.driverModalService.refresh.subscribe((data: Driver) => {
      let index = this.drivers.findIndex(loc => loc.id === data.id)
      if (index !== -1) {
        this.drivers[index] = data;
        this.drivers = [...this.drivers];
      } else
        this.drivers = [...this.drivers, data];
    }))
  }

  loadDrivers() {
    this.subs.push(this.driverService.getAll().subscribe(data => {
      this.drivers = data
    }))
  }

  teamsName(teams: Team[]) {
    return teams.map(team => {
      return team.teamName
    }).toString();
  }

}
