import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMShoot } from 'app/shared/model/m-shoot.model';
import { MShootService } from './m-shoot.service';

@Component({
  selector: 'jhi-m-shoot-delete-dialog',
  templateUrl: './m-shoot-delete-dialog.component.html'
})
export class MShootDeleteDialogComponent {
  mShoot: IMShoot;

  constructor(protected mShootService: MShootService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mShootService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mShootListModification',
        content: 'Deleted an mShoot'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-shoot-delete-popup',
  template: ''
})
export class MShootDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mShoot }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MShootDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mShoot = mShoot;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-shoot', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-shoot', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
