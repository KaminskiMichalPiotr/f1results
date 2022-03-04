import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {Season} from "../shared/models/season.model";
import {ModalOpener} from "../services/modal/modal-opener";
import {ActivatedRoute, Router} from "@angular/router";
import {SeasonService} from "../services/crud/season.service";
import {SeasonModalService} from "../services/modal/season-modal.service";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";

@Component({
  selector: 'app-season-edit',
  templateUrl: './season-edit.component.html',
  styleUrls: ['./season-edit.component.css'],
  providers: [SeasonModalService]
})
export class SeasonEditComponent extends ModalOpener<Season> implements OnInit, OnDestroy {

  subs: Subscription[] = [];

  dataArray: Season[] = [];

  constructor(private seasonService: SeasonService, private modal: SeasonModalService,
              router: Router, route: ActivatedRoute) {
    super(modal, router, route)
  }

  ngOnInit(): void {
    this.loadSeasons();
    this.subs.push(this.modal.refresh
      .subscribe((data: Season) => {
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

  private loadSeasons() {
    this.subs.push(this.seasonService.getAll().subscribe(data => {
      this.dataArray = data
    }))
  }

  sortFn(a: Season, b: Season) {
    return b.seasonYear - a.seasonYear
  }

}
