import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";
import {ModalOpener} from "../services/modal/modal-opener";
import {RaceEvent} from "../shared/models/race-event.model";
import {RaceEventService} from "../services/crud/race-event.service";
import {RaceEventModalService} from "../services/modal/race-event-modal.service";
import {SeasonService} from "../services/crud/season.service";

@Component({
  selector: 'app-race-event-edit',
  templateUrl: './race-event-edit.component.html',
  styleUrls: ['./race-event-edit.component.css']
})
export class RaceEventEditComponent extends ModalOpener<RaceEvent> implements OnInit, OnDestroy {

  subs: Subscription[] = [];
  dataArray: RaceEvent[] = [];
  seasonSelected: boolean = false;
  seasonSelector: number[] = [];
  private selectedSeason!: number;

  constructor(private raceEventService: RaceEventService, private modal: RaceEventModalService,
              router: Router, route: ActivatedRoute, private seasonService: SeasonService) {
    super(modal, router, route)
  }

  ngOnInit(): void {
    this.subs.push(this.seasonService.getSeasonsYears()
      .subscribe(data => this.seasonSelector = data
      ))
    this.subs.push(this.modal.refresh
      .subscribe((data: RaceEvent) => {
        console.log(data);
        this.dataArray = changeElementIfPresentOrAdd(data, this.dataArray);
        console.log(this.dataArray)
      }))
    this.subs.push(this.modal.delete
      .subscribe(data => {
        this.dataArray = deleteElement(data, this.dataArray);
      }))
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe());
  }

  override openEditModal(data: RaceEvent) {
    this.modal.chosenSeason.next(this.selectedSeason)
    super.openEditModal(data);
  }

  override openAddModal() {
    this.modal.chosenSeason.next(this.selectedSeason)
    super.openAddModal();
  }


  extractLocationName(data: RaceEvent): string {
    return data.location.location;
  }

  seasonSelectEvent($event: number) {
    this.selectedSeason = $event;
    this.seasonSelected = true;
    this.loadRaceEvents($event);
  }

  private loadRaceEvents(year: number) {
    this.subs.push(this.raceEventService.getRaceEventsByYear(year).subscribe(data => {
      this.dataArray = data
    }))
  }
}
