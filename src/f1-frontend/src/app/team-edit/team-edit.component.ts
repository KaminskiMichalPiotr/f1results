import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Team} from "../shared/models/team.model";
import {TeamService} from "../services/crud/team.service";
import {TeamModalService} from "../services/modal/team-modal.service";
import {Subscription} from "rxjs";
import {ModalOpener} from "../services/modal/modal-opener";

@Component({
  selector: 'app-team-edit',
  templateUrl: './team-edit.component.html',
  styleUrls: ['./team-edit.component.css']
})
export class TeamEditComponent extends ModalOpener<Team> implements OnInit, OnDestroy {

  subs: Subscription[] = []
  teams: Team[] = [];

  constructor(private teamService: TeamService, private teamModalService: TeamModalService,
              router: Router, route: ActivatedRoute) {
    super(teamModalService, router, route)
  }

  ngOnInit(): void {
    this.teamService.getAll().subscribe(data => {
      this.teams = data
    })
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe())
  }


}
