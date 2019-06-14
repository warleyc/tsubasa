import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMStamp } from 'app/shared/model/m-stamp.model';
import { MStampService } from './m-stamp.service';

@Component({
  selector: 'jhi-m-stamp-delete-dialog',
  templateUrl: './m-stamp-delete-dialog.component.html'
})
export class MStampDeleteDialogComponent {
  mStamp: IMStamp;

  constructor(protected mStampService: MStampService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mStampService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mStampListModification',
        content: 'Deleted an mStamp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-stamp-delete-popup',
  template: ''
})
export class MStampDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStamp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MStampDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mStamp = mStamp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-stamp', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-stamp', { outlets: { popup: null } }]);
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
