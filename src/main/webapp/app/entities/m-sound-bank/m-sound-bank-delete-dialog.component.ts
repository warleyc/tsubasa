import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSoundBank } from 'app/shared/model/m-sound-bank.model';
import { MSoundBankService } from './m-sound-bank.service';

@Component({
  selector: 'jhi-m-sound-bank-delete-dialog',
  templateUrl: './m-sound-bank-delete-dialog.component.html'
})
export class MSoundBankDeleteDialogComponent {
  mSoundBank: IMSoundBank;

  constructor(
    protected mSoundBankService: MSoundBankService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSoundBankService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSoundBankListModification',
        content: 'Deleted an mSoundBank'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-sound-bank-delete-popup',
  template: ''
})
export class MSoundBankDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSoundBank }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSoundBankDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mSoundBank = mSoundBank;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-sound-bank', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-sound-bank', { outlets: { popup: null } }]);
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
