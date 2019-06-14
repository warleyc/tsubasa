import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMKeeperCutAction } from 'app/shared/model/m-keeper-cut-action.model';
import { MKeeperCutActionService } from './m-keeper-cut-action.service';

@Component({
  selector: 'jhi-m-keeper-cut-action-delete-dialog',
  templateUrl: './m-keeper-cut-action-delete-dialog.component.html'
})
export class MKeeperCutActionDeleteDialogComponent {
  mKeeperCutAction: IMKeeperCutAction;

  constructor(
    protected mKeeperCutActionService: MKeeperCutActionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mKeeperCutActionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mKeeperCutActionListModification',
        content: 'Deleted an mKeeperCutAction'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-keeper-cut-action-delete-popup',
  template: ''
})
export class MKeeperCutActionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mKeeperCutAction }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MKeeperCutActionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mKeeperCutAction = mKeeperCutAction;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-keeper-cut-action', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-keeper-cut-action', { outlets: { popup: null } }]);
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
