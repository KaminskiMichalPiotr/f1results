import {Component, OnDestroy, OnInit} from '@angular/core';
import {emptyTeam, Team} from "../../shared/models/team.model";
import {Subscription} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TeamService} from "../../services/crud/team.service";
import {ActivatedRoute, Router} from "@angular/router";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {TeamModalService} from "../../services/modal/team-modal.service";
import {NotificationService} from "../../services/notification.service";

@Component({
  selector: 'app-team-editor-modal',
  templateUrl: './team-editor-modal.component.html',
  styleUrls: ['./team-editor-modal.component.css']
})
export class TeamEditorModalComponent implements OnInit, OnDestroy {

  subs: Subscription[] = []
  isVisible: boolean = true;
  deleteEnable = false;

  team: Team = emptyTeam();
  form!: FormGroup;

  constructor(private fb: FormBuilder, private teamModalService: TeamModalService,
              private teamService: TeamService, private router: Router,
              private route: ActivatedRoute, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.deleteEnable = true;
      this.subs.push(this.teamModalService.selected.subscribe(
        data => this.populateForm(data)))
    } else {
      this.populateForm(this.team)
    }
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  handleOk(): void {
    if (this.form.valid) {
      let teamToSave = this.extractTeam();
      this.subs.push(this.teamService.save(teamToSave).subscribe({
        next: data => {
          this.router.navigate(['../'], {relativeTo: this.route}).then();
          this.notificationService.notification.next({success: true, successMsg: "Team  successfully saved"});
          this.teamModalService.refresh.next(data);
        },
        error: error => {
          this.notificationService.notification.next({success: false, errors: error.error.errors});
        }
      }));
    }
  }

  handleDelete(): void {

  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route})
  }


  initForm() {
    this.form = this.fb.group({
      teamName: ['', [Validators.required, noWhitespaceValidator]],
      teamTag: ['', [Validators.required, noWhitespaceValidator]],
      country: ['', [Validators.required, noWhitespaceValidator]],
    })
  }

  populateForm(teamChange: Team) {
    this.team = teamChange;
    this.form.controls['teamName'].setValue(teamChange.teamName);
    this.form.controls['teamTag'].setValue(teamChange.teamTag);
    this.form.controls['country'].setValue(teamChange.country);
  }

  private extractTeam(): Team {
    let teamToSave = this.form.value as Team;
    teamToSave.id = this.team.id;
    return teamToSave
  }
}
