import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Team} from "../../models/team.model";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {Driver, emptyDriver} from "../../models/driver.model";
import {DriverModalService} from "../../services/driver-modal.service";
import {formatDate} from '@angular/common';
import {convertToDate} from "../../shared/date.converter";
import {TeamService} from "../../services/team.service";
import {DriverService} from "../../services/driver.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-driver-editor-modal',
  templateUrl: './driver-editor-modal.component.html',
  styleUrls: ['./driver-editor-modal.component.css'],
  providers: [TeamService]
})
export class DriverEditorModalComponent implements OnInit {

  isVisible: boolean = true;

  driver!: Driver;
  listOfTeams: Array<Team> = [];
  form: any;
  private sub?: Subscription = undefined;

  constructor(private fb: FormBuilder, private driverModalService: DriverModalService,
              private teamService: TeamService, private driverService: DriverService,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.loadTeams();
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.sub = this.driverModalService.selectedDriver.subscribe(data => this.populateForm(data))
    } else {
      this.populateForm(emptyDriver())
    }
  }

  handleOk(): void {
    let date = formatDate(this.form.get('dateOfBirth').value, 'dd-MM-yyyy', 'en')
    let driver: Driver = {
      driver: this.form.get('driver').value,
      id: this.driver.id,
      dateOfBirth: date,
      nationality: this.form.get('nationality').value,
      teams: this.listOfTeams.filter(team => this.form.get('teams').value.includes(team.id))
    }
    this.driverService.saveDriver(driver).subscribe(data => console.log(data));
    this.router.navigate(['../'], {relativeTo: this.route})
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route})
    if (this.sub)
      this.sub.unsubscribe();
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
    this.teamService.getTeams().subscribe(data => {
      this.listOfTeams = data;
    })
  }

}
