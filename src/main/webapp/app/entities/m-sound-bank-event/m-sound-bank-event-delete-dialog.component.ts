import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSoundBankEvent } from 'app/shared/model/m-sound-bank-event.model';
import { MSoundBankEventService } from './m-sound-bank-event.service';

@Component({
  selector: 'jhi-m-sound-bank-event-delete-dialog',
  templateUrl: './m-sound-bank-event-delete-dialog.component.html'
})
export class MSoundBankEventDeleteDialogComponent {
  mSoundBankEvent: IMSoundBankEvent;

  constructor(
    protected mSoundBankEventService: MSoundBankEventService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSoundBankEventService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSoundBankEventListModification',
        content: 'Deleted an mSoundBankEvent'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-sound-bank-event-delete-popup',
  template: ''
})
export class MSoundBankEventDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSoundBankEvent }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSoundBankEventDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mSoundBankEvent = mSoundBankEvent;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-sound-bank-event', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-sound-bank-event', { outlets: { popup: null } }]);
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
