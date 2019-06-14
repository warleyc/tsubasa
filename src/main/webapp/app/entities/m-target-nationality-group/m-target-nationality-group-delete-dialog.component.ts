import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetNationalityGroup } from 'app/shared/model/m-target-nationality-group.model';
import { MTargetNationalityGroupService } from './m-target-nationality-group.service';

@Component({
  selector: 'jhi-m-target-nationality-group-delete-dialog',
  templateUrl: './m-target-nationality-group-delete-dialog.component.html'
})
export class MTargetNationalityGroupDeleteDialogComponent {
  mTargetNationalityGroup: IMTargetNationalityGroup;

  constructor(
    protected mTargetNationalityGroupService: MTargetNationalityGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetNationalityGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetNationalityGroupListModification',
        content: 'Deleted an mTargetNationalityGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-nationality-group-delete-popup',
  template: ''
})
export class MTargetNationalityGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetNationalityGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetNationalityGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTargetNationalityGroup = mTargetNationalityGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-nationality-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-nationality-group', { outlets: { popup: null } }]);
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
