import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';
import { MQuestGoalRewardService } from './m-quest-goal-reward.service';

@Component({
  selector: 'jhi-m-quest-goal-reward-delete-dialog',
  templateUrl: './m-quest-goal-reward-delete-dialog.component.html'
})
export class MQuestGoalRewardDeleteDialogComponent {
  mQuestGoalReward: IMQuestGoalReward;

  constructor(
    protected mQuestGoalRewardService: MQuestGoalRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestGoalRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestGoalRewardListModification',
        content: 'Deleted an mQuestGoalReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-goal-reward-delete-popup',
  template: ''
})
export class MQuestGoalRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestGoalReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestGoalRewardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestGoalReward = mQuestGoalReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-goal-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-goal-reward', { outlets: { popup: null } }]);
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
