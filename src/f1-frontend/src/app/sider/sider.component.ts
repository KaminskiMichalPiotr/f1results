import {Component, OnDestroy, OnInit} from '@angular/core';
import {SiderCollapseService} from "../services/sider-collapse.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-sider',
  templateUrl: './sider.component.html',
  styleUrls: ['./sider.component.css'],
})
export class SiderComponent implements OnInit, OnDestroy {
  isCollapsed: boolean = false;
  siderCollapseSubscription!: Subscription;

  constructor(private siderCollapseService: SiderCollapseService) {
  }

  ngOnInit(): void {
    this.siderCollapseSubscription = this.siderCollapseService.siderCollapse.subscribe(val => (this.isCollapsed = val))
  }

  ngOnDestroy() {
    this.siderCollapseSubscription.unsubscribe();
  }

}
