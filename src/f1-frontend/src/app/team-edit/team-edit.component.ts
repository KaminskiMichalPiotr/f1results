import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Team} from "../models/team.model";
import {TeamService} from "../services/team.service";
import {TeamModalService} from "../services/team-modal.service";

@Component({
  selector: 'app-team-edit',
  templateUrl: './team-edit.component.html',
  styleUrls: ['./team-edit.component.css']
})
export class TeamEditComponent implements OnInit {

  teams: Team[] = [];

  constructor(private teamService: TeamService, private teamModalService: TeamModalService,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.teamService.getTeams().subscribe(data => {
      this.teams = data
    })
  }

  openEditModal(data: Team) {
    this.teamModalService.selectedTeam.next(data);
    this.router.navigate(['edit'], {relativeTo: this.route})
  }

  openAddModal() {
    this.router.navigate(['add'], {relativeTo: this.route})
  }

}
