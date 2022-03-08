import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {NotificationService} from "../../services/notification.service";
import {emptySeason, Season} from "../../shared/models/season.model";
import {SeasonModalService} from "../../services/modal/season-modal.service";
import {SeasonService} from "../../services/crud/season.service";

@Component({
  selector: 'app-season-editor-modal',
  templateUrl: './season-editor-modal.component.html',
  styleUrls: ['./season-editor-modal.component.css']
})
export class SeasonEditorModalComponent implements OnInit, OnDestroy {

  deleteEnable = false;
  season: Season = emptySeason();
  form!: FormGroup;
  private subs: Subscription[] = [];

  constructor(private fb: FormBuilder, private seasonModalService: SeasonModalService,
              private seasonService: SeasonService, private router: Router,
              private route: ActivatedRoute, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.initForm();
    if (this.router.url.includes('edit')) {
      this.deleteEnable = true;
      this.subs.push(this.seasonModalService.selected.subscribe(
        data => this.populateForm(data)
      ));
    }
  }

  ngOnDestroy(): void {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  handleOk(): void {
    if (this.form.valid) {
      let seasonToSave = this.extractSeason();
      this.subs.push(this.seasonService.save(seasonToSave).subscribe({
        next: data => {
          this.router.navigate(['../'], {relativeTo: this.route}).then();
          this.notificationService.notification.next({success: true, successMsg: "Season successfully updated"});
          this.seasonModalService.refresh.next(data);
        },
        error: error => {
          this.notificationService.notification.next({success: false, errors: error.error.errors});
        }
      }));
    }
  }

  handleDelete(): void {
    this.seasonModalService.delete.next(this.season);
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  handleCancel(): void {
    this.router.navigate(['../'], {relativeTo: this.route}).then()
  }

  initForm() {
    this.form = this.fb.group({
      seasonYear: ['', [Validators.required]]
    })
  }

  populateForm(seasonChange: Season) {
    this.season = seasonChange;
    this.form.controls['seasonYear'].setValue(seasonChange.seasonYear);
  }

  private extractSeason() {
    let seasonToSave = this.form.value as Season
    seasonToSave.id = this.season.id;
    return seasonToSave;
  }


}
