import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetActionTypeGroup } from 'app/shared/model/m-target-action-type-group.model';
import { MTargetActionTypeGroupService } from './m-target-action-type-group.service';

@Component({
  selector: 'jhi-m-target-action-type-group-delete-dialog',
  templateUrl: './m-target-action-type-group-delete-dialog.component.html'
})
export class MTargetActionTypeGroupDeleteDialogComponent {
  mTargetActionTypeGroup: IMTargetActionTypeGroup;

  constructor(
    protected mTargetActionTypeGroupService: MTargetActionTypeGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetActionTypeGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetActionTypeGroupListModification',
        content: 'Deleted an mTargetActionTypeGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-action-type-group-delete-popup',
  template: ''
})
export class MTargetActionTypeGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetActionTypeGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetActionTypeGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTargetActionTypeGroup = mTargetActionTypeGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-action-type-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-action-type-group', { outlets: { popup: null } }]);
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
