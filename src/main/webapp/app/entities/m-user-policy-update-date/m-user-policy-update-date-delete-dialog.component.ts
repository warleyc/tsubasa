import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMUserPolicyUpdateDate } from 'app/shared/model/m-user-policy-update-date.model';
import { MUserPolicyUpdateDateService } from './m-user-policy-update-date.service';

@Component({
  selector: 'jhi-m-user-policy-update-date-delete-dialog',
  templateUrl: './m-user-policy-update-date-delete-dialog.component.html'
})
export class MUserPolicyUpdateDateDeleteDialogComponent {
  mUserPolicyUpdateDate: IMUserPolicyUpdateDate;

  constructor(
    protected mUserPolicyUpdateDateService: MUserPolicyUpdateDateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mUserPolicyUpdateDateService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mUserPolicyUpdateDateListModification',
        content: 'Deleted an mUserPolicyUpdateDate'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-user-policy-update-date-delete-popup',
  template: ''
})
export class MUserPolicyUpdateDateDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUserPolicyUpdateDate }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MUserPolicyUpdateDateDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mUserPolicyUpdateDate = mUserPolicyUpdateDate;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-user-policy-update-date', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-user-policy-update-date', { outlets: { popup: null } }]);
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
