import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SeasonResultComponent} from "./season-result/season-result.component";
import {DriverEditComponent} from "./driver-edit/driver-edit.component";
import {TeamEditComponent} from "./team-edit/team-edit.component";
import {SeasonEditComponent} from "./season-edit/season-edit.component";
import {NotFoundComponent} from "./not-found/not-found.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'home', pathMatch: 'full', component: HomeComponent},
  {path: '2019', pathMatch: 'full', component: SeasonResultComponent},
  {path: '2020', pathMatch: 'full', component: SeasonResultComponent},
  {path: 'manage/driver', pathMatch: 'full', component: DriverEditComponent},
  {path: 'manage/team', pathMatch: 'full', component: TeamEditComponent},
  {path: 'manage/season', pathMatch: 'full', component: SeasonEditComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
