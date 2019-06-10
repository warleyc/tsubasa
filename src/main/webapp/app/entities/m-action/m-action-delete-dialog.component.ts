import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAction } from 'app/shared/model/m-action.model';
import { MActionService } from './m-action.service';

@Component({
  selector: 'jhi-m-action-delete-dialog',
  templateUrl: './m-action-delete-dialog.component.html'
})
export class MActionDeleteDialogComponent {
  mAction: IMAction;

  constructor(protected mActionService: MActionService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mActionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mActionListModification',
        content: 'Deleted an mAction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-action-delete-popup',
  template: ''
})
export class MActionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MActionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAction = mAction;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-action', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-action', { outlets: { popup: null } }]);
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
