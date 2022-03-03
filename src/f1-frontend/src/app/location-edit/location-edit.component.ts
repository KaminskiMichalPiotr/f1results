import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LocationService} from "../services/crud/location.service";
import {LocationModalService} from "../services/modal/location-modal.service";
import {Location} from "../shared/models/location.model";
import {Subscription} from "rxjs";
import {ModalOpener} from "../services/modal/modal-opener";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";

@Component({
  selector: 'app-location-edit',
  templateUrl: './location-edit.component.html',
  styleUrls: ['./location-edit.component.css'],
  providers: [LocationModalService, LocationService]
})
export class LocationEditComponent extends ModalOpener<Location> implements OnInit, OnDestroy {

  subs: Subscription[] = [];

  locations: Location[] = [];

  constructor(private locationService: LocationService, private locationModalService: LocationModalService,
              router: Router, route: ActivatedRoute) {
    super(locationModalService, router, route);
  }

  ngOnInit(): void {
    this.loadLocations();
    this.subs.push(this.locationModalService.refresh.subscribe((data: Location) =>
      this.locations = changeElementIfPresentOrAdd(data, this.locations)))
    this.subs.push(this.locationModalService.delete
      .subscribe(location => this.locations = deleteElement(location, this.locations)))
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  private loadLocations() {
    this.subs.push(this.locationService.getAll().subscribe(data => {
      this.locations = data
    }))
  }


}
