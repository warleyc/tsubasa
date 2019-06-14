import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';
import { MTimeattackQuestStageRewardService } from './m-timeattack-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-timeattack-quest-stage-reward-delete-dialog',
  templateUrl: './m-timeattack-quest-stage-reward-delete-dialog.component.html'
})
export class MTimeattackQuestStageRewardDeleteDialogComponent {
  mTimeattackQuestStageReward: IMTimeattackQuestStageReward;

  constructor(
    protected mTimeattackQuestStageRewardService: MTimeattackQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTimeattackQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTimeattackQuestStageRewardListModification',
        content: 'Deleted an mTimeattackQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-timeattack-quest-stage-reward-delete-popup',
  template: ''
})
export class MTimeattackQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTimeattackQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTimeattackQuestStageReward = mTimeattackQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-timeattack-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-timeattack-quest-stage-reward', { outlets: { popup: null } }]);
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
