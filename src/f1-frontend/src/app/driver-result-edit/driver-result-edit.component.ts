import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Subscription} from "rxjs";
import {SeasonService} from "../services/crud/season.service";
import {ActivatedRoute, Router} from "@angular/router";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";
import {ModalOpener} from "../services/modal/modal-opener";
import {DriverResult} from "../shared/models/driver-result.model";
import {DriverResultService} from "../services/crud/driver-result.service";
import {DriverResultModalService} from "../services/modal/driver-result-modal.service";
import {RaceEvent} from "../shared/models/race-event.model";
import {RaceEventService} from "../services/crud/race-event.service";

@Component({
  selector: 'app-driver-result-edit',
  templateUrl: './driver-result-edit.component.html',
  styleUrls: ['./driver-result-edit.component.css']
})
export class DriverResultEditComponent extends ModalOpener<DriverResult> implements OnInit {

  subs: Subscription[] = [];

  dataArray: DriverResult[] = [];

  seasonSelector: number[] = [];
  raceSelector: RaceEvent[] = [];
  seasonSelected: boolean = false;
  raceSelected: boolean = false;

  selectedYear!: number;
  selectedRaceId!: number;

  constructor(private driverResultService: DriverResultService,
              private modal: DriverResultModalService,
              router: Router, route: ActivatedRoute,
              private seasonService: SeasonService,
              private raceEventService: RaceEventService) {
    super(modal, router, route)
  }

  ngOnInit(): void {
    this.loadSeasons();
    this.subs.push(this.modal.refresh
      .subscribe((data: DriverResult) => {
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

  seasonSelectEvent($event: number) {
    this.seasonSelected = true;
    this.selectedYear = $event;
    this.loadRaces($event);
  }

  raceSelectEvent($event: number) {
    this.raceSelected = true;
    this.selectedRaceId = $event;
    this.loadDriverResults($event);
  }

  extractTeamName(driverResult: DriverResult): string {
    if (driverResult.team !== undefined)
      return driverResult.team.teamName
    else
      return ''
  }

  extractDriverName(driverResult: DriverResult): string {
    if (driverResult.driver !== undefined)
      return driverResult.driver.driver
    else
      return ''
  }

  sortPosition(a: DriverResult, b: DriverResult) {
    return b.position - a.position;
  }

  override openEditModal(data: DriverResult) {
    this.modal.chosenRace = new BehaviorSubject({raceId: this.selectedRaceId, year: this.selectedYear})
    super.openEditModal(data);
  }

  override openAddModal() {
    super.openAddModal();
  }

  private loadDriverResults(raceId: number) {
    this.subs.push(this.driverResultService.getDriverResultByRaceId(raceId).subscribe(data => {
      this.dataArray = data
      console.log(data);
    }))
  }

  private loadSeasons() {
    this.subs.push(this.seasonService.getSeasonsYears()
      .subscribe(data => this.seasonSelector = data.sort((a, b) => b - a)))
  }

  private loadRaces(year: number) {
    this.subs.push(this.raceEventService.getRaceEventsByYear(year)
      .subscribe(data => {
        this.raceSelector = data
      }))
  }
}
