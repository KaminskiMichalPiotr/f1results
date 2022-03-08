import {Component, OnInit} from '@angular/core';
import {SiderCollapseService} from "../services/sider-collapse.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isCollapsed: boolean = false;
  isAdmin: boolean = true;

  constructor(private siderCollapseService: SiderCollapseService) {
  }

  ngOnInit(): void {

  }

  onClick(): void {
    this.isCollapsed = !this.isCollapsed;
    this.siderCollapseService.siderCollapse.next(this.isCollapsed);
  }

}
