import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestStageConditionDescription } from 'app/shared/model/m-quest-stage-condition-description.model';
import { MQuestStageConditionDescriptionService } from './m-quest-stage-condition-description.service';

@Component({
  selector: 'jhi-m-quest-stage-condition-description-delete-dialog',
  templateUrl: './m-quest-stage-condition-description-delete-dialog.component.html'
})
export class MQuestStageConditionDescriptionDeleteDialogComponent {
  mQuestStageConditionDescription: IMQuestStageConditionDescription;

  constructor(
    protected mQuestStageConditionDescriptionService: MQuestStageConditionDescriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestStageConditionDescriptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestStageConditionDescriptionListModification',
        content: 'Deleted an mQuestStageConditionDescription'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-stage-condition-description-delete-popup',
  template: ''
})
export class MQuestStageConditionDescriptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStageConditionDescription }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestStageConditionDescriptionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestStageConditionDescription = mQuestStageConditionDescription;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-stage-condition-description', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-stage-condition-description', { outlets: { popup: null } }]);
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
