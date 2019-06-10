import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';
import { MChallengeQuestStageRewardService } from './m-challenge-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-challenge-quest-stage-reward-delete-dialog',
  templateUrl: './m-challenge-quest-stage-reward-delete-dialog.component.html'
})
export class MChallengeQuestStageRewardDeleteDialogComponent {
  mChallengeQuestStageReward: IMChallengeQuestStageReward;

  constructor(
    protected mChallengeQuestStageRewardService: MChallengeQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mChallengeQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mChallengeQuestStageRewardListModification',
        content: 'Deleted an mChallengeQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-challenge-quest-stage-reward-delete-popup',
  template: ''
})
export class MChallengeQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MChallengeQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mChallengeQuestStageReward = mChallengeQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-challenge-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-challenge-quest-stage-reward', { outlets: { popup: null } }]);
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
