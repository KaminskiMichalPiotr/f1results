import {Component, OnInit} from '@angular/core';
import {emptyTeam, Team} from "../../models/team.model";
import {Subscription} from "rxjs";
import {FormBuilder, Validators} from "@angular/forms";
import {TeamService} from "../../services/team.service";
import {ActivatedRoute, Router} from "@angular/router";
import {noWhitespaceValidator} from "../../shared/whitespace.validator";
import {TeamModalService} from "../../services/team-modal.service";

@Component({
  selector: 'app-team-editor-modal',
  templateUrl: './team-editor-modal.component.html',
  styleUrls: ['./team-editor-modal.component.css']
})
export class TeamEditorModalComponent implements OnInit {

  isVisible: boolean = true;

  team!: Team;
  form: any;
  private sub?: Subscription = undefined;

  constructor(private fb: FormBuilder, private teamModalService: TeamModalService,
              private teamService: TeamService, private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.sub = this.teamModalService.selectedTeam.subscribe(data => this.populateForm(data))
    } else {
      this.populateForm(emptyTeam())
    }
  }

  handleOk(): void {
    this.router.navigate(['../'], {relativeTo: this.route})
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route})
    if (this.sub)
      this.sub.unsubscribe();
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


}
