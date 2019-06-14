import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';
import { MLuckWeeklyQuestStageRewardService } from './m-luck-weekly-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-reward-delete-dialog',
  templateUrl: './m-luck-weekly-quest-stage-reward-delete-dialog.component.html'
})
export class MLuckWeeklyQuestStageRewardDeleteDialogComponent {
  mLuckWeeklyQuestStageReward: IMLuckWeeklyQuestStageReward;

  constructor(
    protected mLuckWeeklyQuestStageRewardService: MLuckWeeklyQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLuckWeeklyQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLuckWeeklyQuestStageRewardListModification',
        content: 'Deleted an mLuckWeeklyQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-reward-delete-popup',
  template: ''
})
export class MLuckWeeklyQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLuckWeeklyQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-luck-weekly-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-luck-weekly-quest-stage-reward', { outlets: { popup: null } }]);
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
