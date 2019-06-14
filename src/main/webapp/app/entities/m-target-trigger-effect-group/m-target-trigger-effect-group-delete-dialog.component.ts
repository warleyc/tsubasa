import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';
import { MTargetTriggerEffectGroupService } from './m-target-trigger-effect-group.service';

@Component({
  selector: 'jhi-m-target-trigger-effect-group-delete-dialog',
  templateUrl: './m-target-trigger-effect-group-delete-dialog.component.html'
})
export class MTargetTriggerEffectGroupDeleteDialogComponent {
  mTargetTriggerEffectGroup: IMTargetTriggerEffectGroup;

  constructor(
    protected mTargetTriggerEffectGroupService: MTargetTriggerEffectGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetTriggerEffectGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetTriggerEffectGroupListModification',
        content: 'Deleted an mTargetTriggerEffectGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-trigger-effect-group-delete-popup',
  template: ''
})
export class MTargetTriggerEffectGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetTriggerEffectGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetTriggerEffectGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTargetTriggerEffectGroup = mTargetTriggerEffectGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-trigger-effect-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-trigger-effect-group', { outlets: { popup: null } }]);
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
