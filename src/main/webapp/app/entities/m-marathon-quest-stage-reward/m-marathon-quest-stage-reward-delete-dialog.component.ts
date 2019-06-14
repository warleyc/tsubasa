import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';
import { MMarathonQuestStageRewardService } from './m-marathon-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-marathon-quest-stage-reward-delete-dialog',
  templateUrl: './m-marathon-quest-stage-reward-delete-dialog.component.html'
})
export class MMarathonQuestStageRewardDeleteDialogComponent {
  mMarathonQuestStageReward: IMMarathonQuestStageReward;

  constructor(
    protected mMarathonQuestStageRewardService: MMarathonQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonQuestStageRewardListModification',
        content: 'Deleted an mMarathonQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-quest-stage-reward-delete-popup',
  template: ''
})
export class MMarathonQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonQuestStageReward = mMarathonQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-quest-stage-reward', { outlets: { popup: null } }]);
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
