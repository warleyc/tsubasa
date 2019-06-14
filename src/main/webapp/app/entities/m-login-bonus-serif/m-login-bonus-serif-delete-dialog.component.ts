import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLoginBonusSerif } from 'app/shared/model/m-login-bonus-serif.model';
import { MLoginBonusSerifService } from './m-login-bonus-serif.service';

@Component({
  selector: 'jhi-m-login-bonus-serif-delete-dialog',
  templateUrl: './m-login-bonus-serif-delete-dialog.component.html'
})
export class MLoginBonusSerifDeleteDialogComponent {
  mLoginBonusSerif: IMLoginBonusSerif;

  constructor(
    protected mLoginBonusSerifService: MLoginBonusSerifService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLoginBonusSerifService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLoginBonusSerifListModification',
        content: 'Deleted an mLoginBonusSerif'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-login-bonus-serif-delete-popup',
  template: ''
})
export class MLoginBonusSerifDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLoginBonusSerif }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLoginBonusSerifDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLoginBonusSerif = mLoginBonusSerif;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-login-bonus-serif', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-login-bonus-serif', { outlets: { popup: null } }]);
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
