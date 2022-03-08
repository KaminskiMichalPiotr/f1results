import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {DriverResultService} from "../../services/crud/driver-result.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../services/notification.service";
import {emptyRaceEvent, RaceEvent} from "../../shared/models/race-event.model";
import {RaceEventModalService} from "../../services/modal/race-event-modal.service";
import {Location} from "../../shared/models/location.model";
import {LocationService} from "../../services/crud/location.service";
import {convertDateToString, convertToDate} from "../../shared/date.converter";
import {RaceEventService} from "../../services/crud/race-event.service";

@Component({
  selector: 'app-race-event-editor-modal',
  templateUrl: './race-event-editor-modal.component.html',
  styleUrls: ['./race-event-editor-modal.component.css']
})
export class RaceEventEditorModalComponent implements OnInit {


  locationsToSelect: Location[] = [];
  deleteEnable = false;
  raceEvent: RaceEvent = emptyRaceEvent();


  form!: FormGroup;
  private subs: Subscription[] = [];

  private selectedYear: number = 2020;

  constructor(private fb: FormBuilder, private raceEventModalService: RaceEventModalService,
              private driverResultService: DriverResultService, private router: Router,
              private route: ActivatedRoute, private notificationService: NotificationService,
              private locationService: LocationService, private raceEventService: RaceEventService) {
  }


  ngOnInit(): void {
    this.loadLocations();
    this.initForm();
    this.subs.push(this.raceEventModalService.chosenSeason.subscribe(data => this.selectedYear = data))
    if (this.router.url.includes('edit')) {
      this.deleteEnable = true;
      this.subs.push(this.raceEventModalService.selected.subscribe(
        data => this.populateForm(data)
      ));
    } else {
      this.populateForm(this.raceEvent)
    }
  }


  ngOnDestroy(): void {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  handleOk(): void {
    if (this.form.valid) {
      let raceToSave = this.extractData();
      this.subs.push(this.raceEventService.createRaceEvent(this.selectedYear, raceToSave).subscribe({
        next: data => {
          this.router.navigate(['../'], {relativeTo: this.route}).then();
          this.notificationService.notification.next({success: true, successMsg: "Race event successfully saved"});
          this.raceEventModalService.refresh.next(data);
        },
        error: error => {
          this.notificationService.notification.next({success: false, errors: error.error.errors});
        }
      }));
    }
  }

  handleDelete(): void {
    this.raceEventModalService.delete.next(this.raceEvent);
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  initForm() {
    this.form = this.fb.group({
      index: ['', [Validators.required]],
      location: ['', [Validators.required]],
      dateOfRace: ['', [Validators.required]],
    })
  }

  populateForm(dataChange: RaceEvent) {
    this.raceEvent = dataChange;
    let date = dataChange.dateOfRace ? convertToDate(dataChange.dateOfRace) : new Date();
    this.form.controls['index'].setValue(dataChange.index);
    this.form.controls['location'].setValue(dataChange.location.id);
    this.form.controls['dateOfRace'].setValue(date);
  }


  private loadLocations() {
    this.subs.push(this.locationService.getAll().subscribe(data => this.locationsToSelect = data))
  }

  private extractData(): RaceEvent {
    let i = this.locationsToSelect.findIndex(location => location.id === this.form.value.location)
    let loc: Location = this.locationsToSelect[i]
    let date = convertDateToString(this.form.value.dateOfRace)
    return {
      id: this.raceEvent.id,
      dateOfRace: date,
      index: this.form.value.index,
      location: loc
    }
  }
}
