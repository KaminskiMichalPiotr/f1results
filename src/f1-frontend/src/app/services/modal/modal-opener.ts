import {ModalService} from "./modal.service";
import {ActivatedRoute, Router} from "@angular/router";

export abstract class ModalOpener<T> {

  modalService: ModalService<T>;
  router: Router;
  route: ActivatedRoute;

  protected constructor(modalService: ModalService<T>, router: Router, route: ActivatedRoute) {
    this.modalService = modalService;
    this.router = router;
    this.route = route;
  }

  openEditModal(data: T) {
    this.modalService.selected.next(data);
    this.router.navigate(['edit'], {relativeTo: this.route}).then()
  }

  openAddModal() {
    this.router.navigate(['add'], {relativeTo: this.route}).then()
  }

}
