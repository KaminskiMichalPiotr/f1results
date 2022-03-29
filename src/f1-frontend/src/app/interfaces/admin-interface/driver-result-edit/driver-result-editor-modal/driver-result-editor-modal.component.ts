import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../../../services/notification.service";
import {DriverResult, emptyDriverResult} from "../../../../shared/models/driver-result.model";
import {DriverResultModalService} from "../../../../services/modal/driver-result-modal.service";
import {DriverResultService} from "../../../../services/crud/driver-result.service";
import {Driver} from "../../../../shared/models/driver.model";
import {Team} from "../../../../shared/models/team.model";
import {DriverService} from "../../../../services/crud/driver.service";
import {TeamService} from "../../../../services/crud/team.service";

@Component({
  selector: 'app-driver-result-editor-modal',
  templateUrl: './driver-result-editor-modal.component.html',
  styleUrls: ['./driver-result-editor-modal.component.css']
})
export class DriverResultEditorModalComponent implements OnInit {


  deleteEnable = false;
  driverResult: DriverResult = emptyDriverResult();
  form!: FormGroup;
  driversToSelect: Driver[] = [];
  teamToSelect: Team[] = [];
  private subs: Subscription[] = [];
  private selectedRaceId!: number;

  constructor(private fb: FormBuilder, private driverResultModalService: DriverResultModalService,
              private driverResultService: DriverResultService, private router: Router,
              private route: ActivatedRoute, private notificationService: NotificationService,
              private driverService: DriverService, private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.initForm();
    this.subs.push(this.driverResultModalService.chosenRace.subscribe(data => this.selectedRaceId = data))
    if (this.router.url.includes('edit')) {
      this.deleteEnable = true;
      this.subs.push(this.driverResultModalService.selected.subscribe(
        data => this.populateForm(data)
      ));
    } else {
      this.populateForm(this.driverResult)
    }
    this.loadDriversAndTeams();
  }


  ngOnDestroy(): void {
    this.subs.forEach(sub => sub.unsubscribe());
  }


  handleOk(): void {
    if (this.form.valid) {
      let driverResultToSave = this.extractData();
      this.subs.push(this.driverResultService.createDriverResult(this.selectedRaceId, driverResultToSave).subscribe({
        next: data => {
          this.router.navigate(['../'], {relativeTo: this.route}).then();
          this.notificationService.notification.next({success: true, successMsg: "Driver Result successfully saved"});
          this.driverResultModalService.refresh.next(data);
        },
        error: error => {
          this.notificationService.notification.next({success: false, errors: error.error.errors});
        }
      }));
    }
  }

  handleDelete(): void {
    this.driverResultModalService.delete.next(this.driverResult);
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  initForm() {
    this.form = this.fb.group({
      driver: ['', [Validators.required]],
      team: ['', [Validators.required]],
      position: ['', [Validators.required]],
    })
  }

  populateForm(driverResultChange: DriverResult) {
    this.driverResult = driverResultChange;
    this.form.controls['driver'].setValue(driverResultChange.driver?.id);
    this.form.controls['team'].setValue(driverResultChange.team?.id);
    this.form.controls['position'].setValue(driverResultChange.position === 0 ? '' : driverResultChange.position);
  }

  loadDriversAndTeams() {
    this.subs.push(this.driverService.getAll().subscribe(data => this.driversToSelect = data));
    this.subs.push(this.teamService.getAll().subscribe(data => this.teamToSelect = data));
  }

  private extractData(): DriverResult {
    let i = this.teamToSelect.findIndex(team => team.id === this.form.value.team)
    let team = this.teamToSelect[i];
    i = this.driversToSelect.findIndex(driver => driver.id === this.form.value.driver)
    let driver = this.driversToSelect[i];
    let id = this.driverResult.id;
    let position = this.form.value.position;
    return {id: id, driver: driver, team: team, position: position}
  }

}
