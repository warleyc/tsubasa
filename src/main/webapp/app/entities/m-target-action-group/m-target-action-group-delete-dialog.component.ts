import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetActionGroup } from 'app/shared/model/m-target-action-group.model';
import { MTargetActionGroupService } from './m-target-action-group.service';

@Component({
  selector: 'jhi-m-target-action-group-delete-dialog',
  templateUrl: './m-target-action-group-delete-dialog.component.html'
})
export class MTargetActionGroupDeleteDialogComponent {
  mTargetActionGroup: IMTargetActionGroup;

  constructor(
    protected mTargetActionGroupService: MTargetActionGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetActionGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetActionGroupListModification',
        content: 'Deleted an mTargetActionGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-action-group-delete-popup',
  template: ''
})
export class MTargetActionGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetActionGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetActionGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTargetActionGroup = mTargetActionGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-action-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-action-group', { outlets: { popup: null } }]);
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
