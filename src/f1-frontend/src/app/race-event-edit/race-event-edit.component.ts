import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";
import {ModalOpener} from "../services/modal/modal-opener";
import {RaceEvent} from "../shared/models/race-event.model";
import {RaceEventService} from "../services/crud/race-event.service";
import {RaceEventModalService} from "../services/modal/race-event-modal.service";

@Component({
  selector: 'app-race-event-edit',
  templateUrl: './race-event-edit.component.html',
  styleUrls: ['./race-event-edit.component.css']
})
export class RaceEventEditComponent extends ModalOpener<RaceEvent> implements OnInit, OnDestroy {

  subs: Subscription[] = [];
  dataArray: RaceEvent[] = [];

  constructor(private raceEventService: RaceEventService, private modal: RaceEventModalService,
              router: Router, route: ActivatedRoute) {
    super(modal, router, route)
  }

  ngOnInit(): void {
    this.loadRaceEvents();
    this.subs.push(this.modal.refresh
      .subscribe((data: RaceEvent) => {
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

  extractLocationName(data: RaceEvent): string {
    return data.location.location;
  }

  private loadRaceEvents() {
    this.subs.push(this.raceEventService.getAll().subscribe(data => {
      this.dataArray = data
    }))
  }

}
