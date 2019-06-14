import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';
import { MWeeklyQuestStageRewardService } from './m-weekly-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-weekly-quest-stage-reward-delete-dialog',
  templateUrl: './m-weekly-quest-stage-reward-delete-dialog.component.html'
})
export class MWeeklyQuestStageRewardDeleteDialogComponent {
  mWeeklyQuestStageReward: IMWeeklyQuestStageReward;

  constructor(
    protected mWeeklyQuestStageRewardService: MWeeklyQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mWeeklyQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mWeeklyQuestStageRewardListModification',
        content: 'Deleted an mWeeklyQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-weekly-quest-stage-reward-delete-popup',
  template: ''
})
export class MWeeklyQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MWeeklyQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mWeeklyQuestStageReward = mWeeklyQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-weekly-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-weekly-quest-stage-reward', { outlets: { popup: null } }]);
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
