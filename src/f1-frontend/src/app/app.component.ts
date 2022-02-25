import {Component, OnDestroy, OnInit} from '@angular/core';
import {SiderCollapseService} from "./services/sider-collapse.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [SiderCollapseService]
})
export class AppComponent implements OnInit, OnDestroy {

  isCollapsed: boolean = false;
  siderCollapseSubscription!: Subscription;

  constructor(private siderCollapseService: SiderCollapseService) {

  }

  ngOnInit() {
    this.siderCollapseSubscription = this.siderCollapseService.siderCollapse.subscribe(val => (this.isCollapsed = val));
  }

  ngOnDestroy() {
    this.siderCollapseSubscription.unsubscribe();
  }


  collapse() {
    this.siderCollapseService.siderCollapse.next(this.isCollapsed);
  }
}
