import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCutAction } from 'app/shared/model/m-cut-action.model';
import { MCutActionService } from './m-cut-action.service';

@Component({
  selector: 'jhi-m-cut-action-delete-dialog',
  templateUrl: './m-cut-action-delete-dialog.component.html'
})
export class MCutActionDeleteDialogComponent {
  mCutAction: IMCutAction;

  constructor(
    protected mCutActionService: MCutActionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCutActionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCutActionListModification',
        content: 'Deleted an mCutAction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-cut-action-delete-popup',
  template: ''
})
export class MCutActionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCutAction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCutActionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCutAction = mCutAction;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-cut-action', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-cut-action', { outlets: { popup: null } }]);
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
