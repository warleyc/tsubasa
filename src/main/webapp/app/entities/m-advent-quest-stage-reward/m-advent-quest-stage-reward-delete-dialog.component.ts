import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';
import { MAdventQuestStageRewardService } from './m-advent-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-advent-quest-stage-reward-delete-dialog',
  templateUrl: './m-advent-quest-stage-reward-delete-dialog.component.html'
})
export class MAdventQuestStageRewardDeleteDialogComponent {
  mAdventQuestStageReward: IMAdventQuestStageReward;

  constructor(
    protected mAdventQuestStageRewardService: MAdventQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAdventQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAdventQuestStageRewardListModification',
        content: 'Deleted an mAdventQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-advent-quest-stage-reward-delete-popup',
  template: ''
})
export class MAdventQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAdventQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mAdventQuestStageReward = mAdventQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-advent-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-advent-quest-stage-reward', { outlets: { popup: null } }]);
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
