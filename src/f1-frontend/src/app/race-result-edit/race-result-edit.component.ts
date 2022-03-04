import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";
import {ModalOpener} from "../services/modal/modal-opener";
import {RaceResult} from "../shared/models/race-result.model";
import {RaceResultService} from "../services/crud/race-result.service";
import {RaceResultModalService} from "../services/modal/race-result-modal.service";

@Component({
  selector: 'app-race-result-edit',
  templateUrl: './race-result-edit.component.html',
  styleUrls: ['./race-result-edit.component.css']
})
export class RaceResultEditComponent extends ModalOpener<RaceResult> implements OnInit, OnDestroy {

  subs: Subscription[] = [];

  dataArray: RaceResult[] = [];

  constructor(private raceResultService: RaceResultService, private modal: RaceResultModalService,
              router: Router, route: ActivatedRoute) {
    super(modal, router, route)
  }

  ngOnInit(): void {
    this.loadRaceResults();
    this.subs.push(this.modal.refresh
      .subscribe((data: RaceResult) => {
        this.dataArray = changeElementIfPresentOrAdd(data, this.dataArray);
      }))
    this.subs.push(this.modal.delete
      .subscribe(data => {
        this.dataArray = deleteElement(data, this.dataArray);
      }))
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  private loadRaceResults() {
    this.subs.push(this.raceResultService.getAll().subscribe(data => {
      this.dataArray = data
    }))
  }


}
