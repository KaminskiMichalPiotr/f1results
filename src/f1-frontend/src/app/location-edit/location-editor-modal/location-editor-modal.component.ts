import {Component, OnDestroy, OnInit} from '@angular/core';
import {emptyLocation, Location} from "../../shared/models/location.model";
import {Subscription} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {LocationModalService} from "../../services/modal/location-modal.service";
import {LocationService} from "../../services/crud/location.service";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-location-editor-modal',
  templateUrl: './location-editor-modal.component.html',
  styleUrls: ['./location-editor-modal.component.css']
})
export class LocationEditorModalComponent implements OnInit, OnDestroy {

  deleteEnable = false;

  location: Location = emptyLocation();
  form!: FormGroup;
  private subs: Subscription[] = [];

  constructor(private fb: FormBuilder, private locationModalService: LocationModalService,
              private locationService: LocationService, private router: Router,
              private route: ActivatedRoute, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.deleteEnable = true;
      this.subs.push(this.locationModalService.selected.subscribe(
        data => this.populateForm(data)
      ));
    } else {
      this.populateForm(this.location)
    }
  }

  ngOnDestroy(): void {
    this.subs.forEach(sub => sub.unsubscribe());
  }


  handleOk(): void {
    if (this.form.valid) {
      let locationToSave = this.extractLocation();
      this.subs.push(this.locationService.save(locationToSave).subscribe({
        next: data => {
          this.router.navigate(['../'], {relativeTo: this.route}).then();
          this.notificationService.notification.next({success: true, successMsg: "Location  successfully updated"});
          this.locationModalService.refresh.next(data);
        },
        error: error => {
          this.notificationService.notification.next({success: false, errors: error.error.errors});
        }
      }));
    }
  }

  handleDelete(): void {
    this.locationModalService.delete.next(this.location);
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  initForm() {
    this.form = this.fb.group({
      location: ['', [Validators.required, noWhitespaceValidator]],
      locationTag: ['', [Validators.required, noWhitespaceValidator]],
      country: ['', [Validators.required, noWhitespaceValidator]],
    })
  }

  populateForm(locationChange: Location) {
    this.location = locationChange;
    this.form.controls['location'].setValue(locationChange.location);
    this.form.controls['locationTag'].setValue(locationChange.locationTag);
    this.form.controls['country'].setValue(locationChange.country);
  }

  private extractLocation() {
    let locationToSave = this.form.value as Location
    locationToSave.id = this.location.id;
    return locationToSave;
  }

}
