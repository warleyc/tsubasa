import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLoginBonusRound } from 'app/shared/model/m-login-bonus-round.model';
import { MLoginBonusRoundService } from './m-login-bonus-round.service';

@Component({
  selector: 'jhi-m-login-bonus-round-delete-dialog',
  templateUrl: './m-login-bonus-round-delete-dialog.component.html'
})
export class MLoginBonusRoundDeleteDialogComponent {
  mLoginBonusRound: IMLoginBonusRound;

  constructor(
    protected mLoginBonusRoundService: MLoginBonusRoundService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLoginBonusRoundService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLoginBonusRoundListModification',
        content: 'Deleted an mLoginBonusRound'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-login-bonus-round-delete-popup',
  template: ''
})
export class MLoginBonusRoundDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLoginBonusRound }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLoginBonusRoundDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLoginBonusRound = mLoginBonusRound;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-login-bonus-round', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-login-bonus-round', { outlets: { popup: null } }]);
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
