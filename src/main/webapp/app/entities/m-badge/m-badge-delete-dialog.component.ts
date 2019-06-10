import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMBadge } from 'app/shared/model/m-badge.model';
import { MBadgeService } from './m-badge.service';

@Component({
  selector: 'jhi-m-badge-delete-dialog',
  templateUrl: './m-badge-delete-dialog.component.html'
})
export class MBadgeDeleteDialogComponent {
  mBadge: IMBadge;

  constructor(protected mBadgeService: MBadgeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mBadgeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mBadgeListModification',
        content: 'Deleted an mBadge'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-badge-delete-popup',
  template: ''
})
export class MBadgeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mBadge }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MBadgeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mBadge = mBadge;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-badge', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-badge', { outlets: { popup: null } }]);
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
