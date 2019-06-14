import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';
import { MPenaltyKickCutService } from './m-penalty-kick-cut.service';

@Component({
  selector: 'jhi-m-penalty-kick-cut-delete-dialog',
  templateUrl: './m-penalty-kick-cut-delete-dialog.component.html'
})
export class MPenaltyKickCutDeleteDialogComponent {
  mPenaltyKickCut: IMPenaltyKickCut;

  constructor(
    protected mPenaltyKickCutService: MPenaltyKickCutService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPenaltyKickCutService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPenaltyKickCutListModification',
        content: 'Deleted an mPenaltyKickCut'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-penalty-kick-cut-delete-popup',
  template: ''
})
export class MPenaltyKickCutDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPenaltyKickCut }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPenaltyKickCutDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPenaltyKickCut = mPenaltyKickCut;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-penalty-kick-cut', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-penalty-kick-cut', { outlets: { popup: null } }]);
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
