import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LocationService} from "../services/location.service";
import {LocationModalService} from "../services/location-modal.service";
import {Location} from "../models/location.model";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {NotificationModel} from "../models/notification.model";

@Component({
  selector: 'app-location-edit',
  templateUrl: './location-edit.component.html',
  styleUrls: ['./location-edit.component.css'],
  providers: [LocationModalService, LocationService, NzNotificationService]
})
export class LocationEditComponent implements OnInit {

  locations: Location[] = [];

  constructor(private locationService: LocationService, private locationModalService: LocationModalService,
              private router: Router, private route: ActivatedRoute, private notification: NzNotificationService) {
  }

  ngOnInit(): void {
    this.locationService.getLocations().subscribe(data => {
      this.locations = data
    })
    this.locationModalService.notification.subscribe(data => this.displayNotification(data));

  }

  openEditModal(data: Location) {
    this.locationModalService.selectedLocation.next(data);
    this.router.navigate(['edit'], {relativeTo: this.route})
  }

  openAddModal() {
    this.router.navigate(['add'], {relativeTo: this.route})
  }

  displayNotification(data: NotificationModel) {
    if (data.success) {
      this.notification.success("Success", 'Location updated!')
    } else {
      data.errors?.forEach(error => this.notification.warning("Error:", error))
    }
  }


}
