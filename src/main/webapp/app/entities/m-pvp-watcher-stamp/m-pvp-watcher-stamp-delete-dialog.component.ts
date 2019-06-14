import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';
import { MPvpWatcherStampService } from './m-pvp-watcher-stamp.service';

@Component({
  selector: 'jhi-m-pvp-watcher-stamp-delete-dialog',
  templateUrl: './m-pvp-watcher-stamp-delete-dialog.component.html'
})
export class MPvpWatcherStampDeleteDialogComponent {
  mPvpWatcherStamp: IMPvpWatcherStamp;

  constructor(
    protected mPvpWatcherStampService: MPvpWatcherStampService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpWatcherStampService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpWatcherStampListModification',
        content: 'Deleted an mPvpWatcherStamp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-watcher-stamp-delete-popup',
  template: ''
})
export class MPvpWatcherStampDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpWatcherStamp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpWatcherStampDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPvpWatcherStamp = mPvpWatcherStamp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-watcher-stamp', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-watcher-stamp', { outlets: { popup: null } }]);
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
