import {Component, OnInit} from '@angular/core';
import {emptyLocation, Location} from "../../models/location.model";
import {Subscription, throwError} from "rxjs";
import {FormBuilder, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {LocationModalService} from "../../services/location-modal.service";
import {LocationService} from "../../services/location.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-location-editor-modal',
  templateUrl: './location-editor-modal.component.html',
  styleUrls: ['./location-editor-modal.component.css']
})
export class LocationEditorModalComponent implements OnInit {

  isVisible: boolean = true;

  location!: Location;
  form: any;
  private sub?: Subscription = undefined;

  constructor(private fb: FormBuilder, private locationModalService: LocationModalService,
              private locationService: LocationService, private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.sub = this.locationModalService.selectedLocation.subscribe(data => this.populateForm(data))
    } else {
      this.populateForm(emptyLocation())
    }
  }

  handleOk(): void {
    if (this.form.valid) {
      let locationToSave: Location = {
        id: this.location.id,
        location: this.form.get('location').value,
        locationTag: this.form.get('locationTag').value,
        country: this.form.get('country').value
      }
      console.log(locationToSave);
      this.locationService.saveLocation(locationToSave).subscribe(
        data => {
          this.router.navigate(['../'], {relativeTo: this.route});
          this.locationModalService.notification.next({success: true});
        },
        error => {
          this.locationModalService.notification.next({success: false, errors: error.error.errors});
          console.log(error);
        }
      );
    }
  }

  displaySuccess(data: any) {

  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route})
    if (this.sub)
      this.sub.unsubscribe();
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

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

}
