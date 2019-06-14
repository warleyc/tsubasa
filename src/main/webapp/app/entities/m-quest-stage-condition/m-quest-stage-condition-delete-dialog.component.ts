import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';
import { MQuestStageConditionService } from './m-quest-stage-condition.service';

@Component({
  selector: 'jhi-m-quest-stage-condition-delete-dialog',
  templateUrl: './m-quest-stage-condition-delete-dialog.component.html'
})
export class MQuestStageConditionDeleteDialogComponent {
  mQuestStageCondition: IMQuestStageCondition;

  constructor(
    protected mQuestStageConditionService: MQuestStageConditionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestStageConditionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestStageConditionListModification',
        content: 'Deleted an mQuestStageCondition'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-stage-condition-delete-popup',
  template: ''
})
export class MQuestStageConditionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStageCondition }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestStageConditionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestStageCondition = mQuestStageCondition;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-stage-condition', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-stage-condition', { outlets: { popup: null } }]);
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
