import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';
import { MChallengeQuestAchievementRewardService } from './m-challenge-quest-achievement-reward.service';

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-delete-dialog',
  templateUrl: './m-challenge-quest-achievement-reward-delete-dialog.component.html'
})
export class MChallengeQuestAchievementRewardDeleteDialogComponent {
  mChallengeQuestAchievementReward: IMChallengeQuestAchievementReward;

  constructor(
    protected mChallengeQuestAchievementRewardService: MChallengeQuestAchievementRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mChallengeQuestAchievementRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mChallengeQuestAchievementRewardListModification',
        content: 'Deleted an mChallengeQuestAchievementReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-delete-popup',
  template: ''
})
export class MChallengeQuestAchievementRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestAchievementReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MChallengeQuestAchievementRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mChallengeQuestAchievementReward = mChallengeQuestAchievementReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-challenge-quest-achievement-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-challenge-quest-achievement-reward', { outlets: { popup: null } }]);
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
