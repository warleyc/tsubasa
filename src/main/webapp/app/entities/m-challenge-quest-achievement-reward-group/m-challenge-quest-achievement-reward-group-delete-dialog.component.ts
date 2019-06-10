import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';
import { MChallengeQuestAchievementRewardGroupService } from './m-challenge-quest-achievement-reward-group.service';

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-group-delete-dialog',
  templateUrl: './m-challenge-quest-achievement-reward-group-delete-dialog.component.html'
})
export class MChallengeQuestAchievementRewardGroupDeleteDialogComponent {
  mChallengeQuestAchievementRewardGroup: IMChallengeQuestAchievementRewardGroup;

  constructor(
    protected mChallengeQuestAchievementRewardGroupService: MChallengeQuestAchievementRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mChallengeQuestAchievementRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mChallengeQuestAchievementRewardGroupListModification',
        content: 'Deleted an mChallengeQuestAchievementRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-group-delete-popup',
  template: ''
})
export class MChallengeQuestAchievementRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestAchievementRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MChallengeQuestAchievementRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-challenge-quest-achievement-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-challenge-quest-achievement-reward-group', { outlets: { popup: null } }]);
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
