import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {en_US, NZ_I18N} from 'ng-zorro-antd/i18n';
import {registerLocaleData} from '@angular/common';
import en from '@angular/common/locales/en';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {IconsProviderModule} from './icons-provider.module';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {SeasonResultComponent} from "./interfaces/user-interface/season-result/season-result.component";
import {NzTableModule} from "ng-zorro-antd/table";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {DriverEditComponent} from './interfaces/admin-interface/driver-edit/driver-edit.component';
import {TeamEditComponent} from './interfaces/admin-interface/team-edit/team-edit.component';
import {SeasonEditComponent} from './interfaces/admin-interface/season-edit/season-edit.component';
import {NzModalModule} from "ng-zorro-antd/modal";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NotFoundComponent} from './interfaces/shared-interface/not-found/not-found.component';
import {NzResultModule} from "ng-zorro-antd/result";
import {HomeComponent} from './interfaces/shared-interface/home/home.component';
import {NzSpinModule} from "ng-zorro-antd/spin";
import {
  DriverEditorModalComponent
} from './interfaces/admin-interface/driver-edit/driver-editor-modal/driver-editor-modal.component';
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzFormModule} from "ng-zorro-antd/form";
import {NzDatePickerModule} from "ng-zorro-antd/date-picker";
import {
  TeamEditorModalComponent
} from './interfaces/admin-interface/team-edit/team-editor-modal/team-editor-modal.component';
import {NzInputModule} from "ng-zorro-antd/input";
import {LocationEditComponent} from './interfaces/admin-interface/location-edit/location-edit.component';
import {
  LocationEditorModalComponent
} from './interfaces/admin-interface/location-edit/location-editor-modal/location-editor-modal.component';
import {
  SeasonEditorModalComponent
} from './interfaces/admin-interface/season-edit/season-editor-modal/season-editor-modal.component';
import {NzInputNumberModule} from "ng-zorro-antd/input-number";
import {DriverResultEditComponent} from './interfaces/admin-interface/driver-result-edit/driver-result-edit.component';
import {
  DriverResultEditorModalComponent
} from './interfaces/admin-interface/driver-result-edit/driver-result-editor-modal/driver-result-editor-modal.component';
import {RaceEventEditComponent} from './interfaces/admin-interface/race-event-edit/race-event-edit.component';
import {
  RaceEventEditorModalComponent
} from './interfaces/admin-interface/race-event-edit/race-event-editor-modal/race-event-editor-modal.component';
import {NzPopconfirmModule} from "ng-zorro-antd/popconfirm";
import {LoginComponent} from './interfaces/shared-interface/login/login.component';
import {TokenInterceptor} from "./services/token.interceptor";
import {TeamResultComponent} from './interfaces/user-interface/team-result/team-result.component';
import {HeaderComponent} from "./interfaces/shared-interface/header/header.component";
import {SiderComponent} from "./interfaces/shared-interface/sider/sider.component";


registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    SeasonResultComponent,
    SiderComponent,
    HeaderComponent,
    DriverEditComponent,
    TeamEditComponent,
    SeasonEditComponent,
    NotFoundComponent,
    HomeComponent,
    DriverEditorModalComponent,
    TeamEditorModalComponent,
    LocationEditComponent,
    LocationEditorModalComponent,
    SeasonEditorModalComponent,
    DriverResultEditComponent,
    DriverResultEditorModalComponent,
    RaceEventEditComponent,
    RaceEventEditorModalComponent,
    LoginComponent,
    TeamResultComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    IconsProviderModule,
    NzLayoutModule,
    NzMenuModule,
    NzTableModule,
    NzDividerModule,
    NzModalModule,
    NzButtonModule,
    NzResultModule,
    NzSpinModule,
    NzSelectModule,
    NzFormModule,
    NzDatePickerModule,
    ReactiveFormsModule,
    NzInputModule,
    NzInputNumberModule,
    NzPopconfirmModule
  ],
  providers: [{provide: NZ_I18N, useValue: en_US}, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
