import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {en_US, NZ_I18N} from 'ng-zorro-antd/i18n';
import {registerLocaleData} from '@angular/common';
import en from '@angular/common/locales/en';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {IconsProviderModule} from './icons-provider.module';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {SeasonResultComponent} from "./season-result/season-result.component";
import {NzTableModule} from "ng-zorro-antd/table";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {SiderComponent} from './sider/sider.component';
import {HeaderComponent} from './header/header.component';
import {DriverEditComponent} from './driver-edit/driver-edit.component';
import {TeamEditComponent} from './team-edit/team-edit.component';
import {SeasonEditComponent} from './season-edit/season-edit.component';
import {NzModalModule} from "ng-zorro-antd/modal";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NotFoundComponent} from './not-found/not-found.component';
import {NzResultModule} from "ng-zorro-antd/result";
import {HomeComponent} from './home/home.component';
import {NzSpinModule} from "ng-zorro-antd/spin";
import {DriverEditorModalComponent} from './driver-edit/driver-editor-modal/driver-editor-modal.component';
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzFormModule} from "ng-zorro-antd/form";
import {NzDatePickerModule} from "ng-zorro-antd/date-picker";
import {TeamEditorModalComponent} from './team-edit/team-editor-modal/team-editor-modal.component';
import {NzInputModule} from "ng-zorro-antd/input";


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
    NzInputModule
  ],
  providers: [{provide: NZ_I18N, useValue: en_US}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
