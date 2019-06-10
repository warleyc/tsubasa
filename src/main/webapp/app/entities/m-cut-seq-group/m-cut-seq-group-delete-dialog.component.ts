import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCutSeqGroup } from 'app/shared/model/m-cut-seq-group.model';
import { MCutSeqGroupService } from './m-cut-seq-group.service';

@Component({
  selector: 'jhi-m-cut-seq-group-delete-dialog',
  templateUrl: './m-cut-seq-group-delete-dialog.component.html'
})
export class MCutSeqGroupDeleteDialogComponent {
  mCutSeqGroup: IMCutSeqGroup;

  constructor(
    protected mCutSeqGroupService: MCutSeqGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCutSeqGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCutSeqGroupListModification',
        content: 'Deleted an mCutSeqGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-cut-seq-group-delete-popup',
  template: ''
})
export class MCutSeqGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCutSeqGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCutSeqGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCutSeqGroup = mCutSeqGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-cut-seq-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-cut-seq-group', { outlets: { popup: null } }]);
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
