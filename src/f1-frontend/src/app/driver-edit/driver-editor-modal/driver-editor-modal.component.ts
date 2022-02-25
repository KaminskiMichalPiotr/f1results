import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Team} from "../../models/team.model";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {Driver} from "../../models/driver.model";
import {DriverModalService} from "../../services/driver-modal.service";
import {formatDate} from '@angular/common';
import {convertToDate} from "../../shared/date.converter";
import {TeamService} from "../../services/team.service";
import {DriverService} from "../../services/driver.service";

@Component({
  selector: 'app-driver-editor-modal',
  templateUrl: './driver-editor-modal.component.html',
  styleUrls: ['./driver-editor-modal.component.css'],
  providers: [TeamService]
})
export class DriverEditorModalComponent implements OnInit {

  isVisible: boolean = false;

  @Input() testDriver!: Driver;
  listOfTeams: Array<Team> = [];
  validateForm: any;

  constructor(private fb: FormBuilder, private driverModalService: DriverModalService,
              private teamService: TeamService, private driverService: DriverService) {
  }

  ngOnInit(): void {
    this.testDriver = {driver: '', nationality: '', id: null, teams: [], dateOfBirth: ''}
    this.teamService.getTeams().subscribe(data => {
      this.listOfTeams = data;
      console.log(this.listOfTeams)
    })

    this.validateForm = this.fb.group({
      driver: ['', [Validators.required, noWhitespaceValidator]],
      nationality: ['', [Validators.required, noWhitespaceValidator]],
      dateOfBirth: ['', [Validators.required]],
      teams: [[], [Validators.required]]
    })
    this.driverModalService.modalOpen.subscribe(open => this.isVisible = open);
    this.driverModalService.modalReset.subscribe(reset => {
      if (reset) this.resetForm()
    });
  }

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
    let date = formatDate(this.validateForm.get('dateOfBirth').value, 'dd-MM-yyyy', 'en')
    let driver: Driver = {
      driver: this.validateForm.get('driver').value,
      id: this.testDriver.id, dateOfBirth: date,
      nationality: this.validateForm.get('nationality').value,
      teams: this.listOfTeams.filter(team => this.validateForm.get('teams').value.includes(team.id))
    }
    this.driverService.saveDriver(driver).subscribe(data => console.log(data));
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['testDriver'] && this.validateForm != undefined) {
      let variableChange = changes['testDriver'];
      let driverChange = variableChange.currentValue as Driver;
      this.validateForm.controls['driver'].setValue(driverChange.driver);
      this.validateForm.controls['nationality'].setValue(driverChange.nationality);
      this.validateForm.controls['dateOfBirth'].setValue(convertToDate(driverChange.dateOfBirth));
      this.validateForm.controls['teams'].setValue(driverChange.teams.map(team => {
        return team.id
      }))
    }
  }

  resetForm() {
    this.testDriver.id = null;
    this.validateForm.controls['driver'].setValue('');
    this.validateForm.controls['nationality'].setValue('');
    this.validateForm.controls['dateOfBirth'].setValue(new Date());
    this.validateForm.controls['teams'].setValue([])
  }

}
