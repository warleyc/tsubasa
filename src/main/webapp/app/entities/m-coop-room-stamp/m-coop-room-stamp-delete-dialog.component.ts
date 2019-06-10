import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';
import { MCoopRoomStampService } from './m-coop-room-stamp.service';

@Component({
  selector: 'jhi-m-coop-room-stamp-delete-dialog',
  templateUrl: './m-coop-room-stamp-delete-dialog.component.html'
})
export class MCoopRoomStampDeleteDialogComponent {
  mCoopRoomStamp: IMCoopRoomStamp;

  constructor(
    protected mCoopRoomStampService: MCoopRoomStampService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCoopRoomStampService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCoopRoomStampListModification',
        content: 'Deleted an mCoopRoomStamp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-coop-room-stamp-delete-popup',
  template: ''
})
export class MCoopRoomStampDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCoopRoomStamp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCoopRoomStampDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCoopRoomStamp = mCoopRoomStamp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-coop-room-stamp', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-coop-room-stamp', { outlets: { popup: null } }]);
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
