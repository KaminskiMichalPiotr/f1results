import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Team} from "../../shared/models/team.model";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {Driver, emptyDriver} from "../../shared/models/driver.model";
import {DriverModalService} from "../../services/modal/driver-modal.service";
import {formatDate} from '@angular/common';
import {convertToDate} from "../../shared/date.converter";
import {TeamService} from "../../services/crud/team.service";
import {DriverService} from "../../services/crud/driver.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-driver-editor-modal',
  templateUrl: './driver-editor-modal.component.html',
  styleUrls: ['./driver-editor-modal.component.css'],
  providers: [TeamService]
})
export class DriverEditorModalComponent implements OnInit, OnDestroy {

  isVisible = true;
  subs: Subscription[] = []

  driver!: Driver;
  listOfTeams: Array<Team> = [];
  form: any;

  constructor(private fb: FormBuilder, private driverModalService: DriverModalService,
              private teamService: TeamService, private driverService: DriverService,
              private router: Router, private route: ActivatedRoute,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.loadTeams();
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.subs.push(this.driverModalService.selected.subscribe(data => this.populateForm(data)))
    } else {
      this.populateForm(emptyDriver())
    }
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  handleOk(): void {
    let driver = this.extractDriver();
    this.subs.push(this.driverService.save(driver).subscribe({
      next: data => {
        this.driverModalService.refresh.next(data)
        this.notificationService.notification.next({success: true, successMsg: "Driver successfully updated"});
        this.router.navigate(['../'], {relativeTo: this.route}).then()
      },
      error: error => {
        this.notificationService.notification.next({success: false, errors: error.error.errors});
      }
    }));
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  initForm() {
    this.form = this.fb.group({
      driver: ['', [Validators.required, noWhitespaceValidator]],
      nationality: ['', [Validators.required, noWhitespaceValidator]],
      dateOfBirth: ['', [Validators.required]],
      teams: [[], [Validators.required]]
    })
  }

  populateForm(driverChange: Driver) {
    this.driver = driverChange;
    let date = driverChange.dateOfBirth ? convertToDate(driverChange.dateOfBirth) : new Date();
    this.form.controls['driver'].setValue(driverChange.driver);
    this.form.controls['nationality'].setValue(driverChange.nationality);
    this.form.controls['dateOfBirth'].setValue(date);
    this.form.controls['teams'].setValue(driverChange.teams.map(team => {
      return team.id
    }))
  }

  loadTeams() {
    this.subs.push(this.teamService.getAll().subscribe(data => {
      this.listOfTeams = data;
    }))
  }

  private extractDriver() {
    let date = formatDate(this.form.get('dateOfBirth').value, 'dd-MM-yyyy', 'en')
    let driver: Driver = {
      driver: this.form.get('driver').value,
      id: this.router.url.includes('edit') ? this.driver.id : null,
      dateOfBirth: date,
      nationality: this.form.get('nationality').value,
      teams: this.listOfTeams.filter(team => this.form.get('teams').value.includes(team.id))
    }
    return driver;
  }

}
