import {Component, OnDestroy, OnInit} from '@angular/core';
import {SiderCollapseService} from "./services/sider-collapse.service";
import {Subscription} from "rxjs";
import {NzNotificationService} from "ng-zorro-antd/notification";
import {NotificationModel} from "./shared/models/notification.model";
import {NotificationService} from "./services/notification.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [SiderCollapseService, NotificationService, NzNotificationService]
})
export class AppComponent implements OnInit, OnDestroy {

  isCollapsed: boolean = false;
  siderCollapseSubscription!: Subscription;

  constructor(private siderCollapseService: SiderCollapseService, private notification: NzNotificationService,
              private notificationService: NotificationService) {

  }

  ngOnInit() {
    this.siderCollapseSubscription = this.siderCollapseService.siderCollapse.subscribe(val => (this.isCollapsed = val));
    this.notificationService.notification.subscribe(data => this.displayNotification(data));
  }

  ngOnDestroy() {
    this.siderCollapseSubscription.unsubscribe();
  }

  collapse() {
    this.siderCollapseService.siderCollapse.next(this.isCollapsed);
  }

  displayNotification(data: NotificationModel) {
    if (data.success) {
      this.notification.success("Success", data.successMsg ? data.successMsg : "Success")
    } else {
      data.errors?.forEach(error => this.notification.warning("Error:", error))
    }
  }

}
