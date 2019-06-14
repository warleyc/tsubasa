import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLoginBonusIncentive } from 'app/shared/model/m-login-bonus-incentive.model';
import { MLoginBonusIncentiveService } from './m-login-bonus-incentive.service';

@Component({
  selector: 'jhi-m-login-bonus-incentive-delete-dialog',
  templateUrl: './m-login-bonus-incentive-delete-dialog.component.html'
})
export class MLoginBonusIncentiveDeleteDialogComponent {
  mLoginBonusIncentive: IMLoginBonusIncentive;

  constructor(
    protected mLoginBonusIncentiveService: MLoginBonusIncentiveService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLoginBonusIncentiveService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLoginBonusIncentiveListModification',
        content: 'Deleted an mLoginBonusIncentive'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-login-bonus-incentive-delete-popup',
  template: ''
})
export class MLoginBonusIncentiveDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLoginBonusIncentive }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLoginBonusIncentiveDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLoginBonusIncentive = mLoginBonusIncentive;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-login-bonus-incentive', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-login-bonus-incentive', { outlets: { popup: null } }]);
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
