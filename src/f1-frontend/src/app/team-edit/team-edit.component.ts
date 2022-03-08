import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Team} from "../shared/models/team.model";
import {TeamService} from "../services/crud/team.service";
import {TeamModalService} from "../services/modal/team-modal.service";
import {Subscription} from "rxjs";
import {ModalOpener} from "../services/modal/modal-opener";
import {changeElementIfPresentOrAdd, deleteElement} from "../shared/array.operator";

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
    this.loadTeams();
    this.listenToTeamChanges();
  }

  private loadTeams() {
    this.teamService.getAll().subscribe(data => {
      this.teams = data
    })
  }

  private listenToTeamChanges() {
    this.subs.push(this.teamModalService.refresh
      .subscribe(team => this.teams = changeElementIfPresentOrAdd(team, this.teams)))
    this.subs.push(this.teamModalService.delete
      .subscribe(location => this.teams = deleteElement(location, this.teams)))
  }

  ngOnDestroy() {
    this.subs.forEach(sub => sub.unsubscribe())
  }


}
