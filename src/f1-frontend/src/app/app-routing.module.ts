import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SeasonResultComponent} from "./season-result/season-result.component";
import {DriverEditComponent} from "./driver-edit/driver-edit.component";
import {TeamEditComponent} from "./team-edit/team-edit.component";
import {SeasonEditComponent} from "./season-edit/season-edit.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {HomeComponent} from "./home/home.component";
import {DriverEditorModalComponent} from "./driver-edit/driver-editor-modal/driver-editor-modal.component";
import {TeamEditorModalComponent} from "./team-edit/team-editor-modal/team-editor-modal.component";
import {LocationEditComponent} from "./location-edit/location-edit.component";
import {LocationEditorModalComponent} from "./location-edit/location-editor-modal/location-editor-modal.component";
import {SeasonEditorModalComponent} from "./season-edit/season-editor-modal/season-editor-modal.component";
import {DriverResultEditComponent} from "./driver-result-edit/driver-result-edit.component";
import {RaceEventEditComponent} from "./race-event-edit/race-event-edit.component";
import {
  RaceEventEditorModalComponent
} from "./race-event-edit/race-event-editor-modal/race-event-editor-modal.component";
import {
  DriverResultEditorModalComponent
} from "./driver-result-edit/driver-result-editor-modal/driver-result-editor-modal.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'login', component: LoginComponent},
  {path: 'home', pathMatch: 'full', component: HomeComponent},
  {path: 'seasons', pathMatch: 'full', component: SeasonResultComponent},
  {path: '2020', pathMatch: 'full', component: SeasonResultComponent},
  {
    path: 'manage/drivers', component: DriverEditComponent, children: [
      {path: 'add', component: DriverEditorModalComponent},
      {path: 'edit', component: DriverEditorModalComponent}
    ]
  },
  {
    path: 'manage/teams', component: TeamEditComponent, children: [
      {path: 'add', component: TeamEditorModalComponent},
      {path: 'edit', component: TeamEditorModalComponent}
    ]
  },
  {
    path: 'manage/seasons', component: SeasonEditComponent, children: [
      {path: 'add', component: SeasonEditorModalComponent},
      {path: 'edit', component: SeasonEditorModalComponent}
    ]
  },
  {
    path: 'manage/locations', component: LocationEditComponent, children: [
      {path: 'add', component: LocationEditorModalComponent},
      {path: 'edit', component: LocationEditorModalComponent}
    ]
  },
  {
    path: 'manage/race-events', component: RaceEventEditComponent, children: [
      {path: 'add', component: RaceEventEditorModalComponent},
      {path: 'edit', component: RaceEventEditorModalComponent}
    ]
  },
  {
    path: 'manage/driver-results', component: DriverResultEditComponent, children: [
      {path: 'add', component: DriverResultEditorModalComponent},
      {path: 'edit', component: DriverResultEditorModalComponent}
    ]
  },
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
