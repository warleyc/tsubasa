import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';
import { MPvpPlayerStampService } from './m-pvp-player-stamp.service';

@Component({
  selector: 'jhi-m-pvp-player-stamp-delete-dialog',
  templateUrl: './m-pvp-player-stamp-delete-dialog.component.html'
})
export class MPvpPlayerStampDeleteDialogComponent {
  mPvpPlayerStamp: IMPvpPlayerStamp;

  constructor(
    protected mPvpPlayerStampService: MPvpPlayerStampService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpPlayerStampService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpPlayerStampListModification',
        content: 'Deleted an mPvpPlayerStamp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-player-stamp-delete-popup',
  template: ''
})
export class MPvpPlayerStampDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpPlayerStamp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpPlayerStampDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPvpPlayerStamp = mPvpPlayerStamp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-player-stamp', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-player-stamp', { outlets: { popup: null } }]);
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
