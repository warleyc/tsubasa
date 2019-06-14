import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetFormationGroup } from 'app/shared/model/m-target-formation-group.model';
import { MTargetFormationGroupService } from './m-target-formation-group.service';

@Component({
  selector: 'jhi-m-target-formation-group-delete-dialog',
  templateUrl: './m-target-formation-group-delete-dialog.component.html'
})
export class MTargetFormationGroupDeleteDialogComponent {
  mTargetFormationGroup: IMTargetFormationGroup;

  constructor(
    protected mTargetFormationGroupService: MTargetFormationGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetFormationGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetFormationGroupListModification',
        content: 'Deleted an mTargetFormationGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-formation-group-delete-popup',
  template: ''
})
export class MTargetFormationGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetFormationGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetFormationGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTargetFormationGroup = mTargetFormationGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-formation-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-formation-group', { outlets: { popup: null } }]);
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
