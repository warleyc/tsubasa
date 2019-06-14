import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';
import { MQuestStageRewardService } from './m-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-quest-stage-reward-delete-dialog',
  templateUrl: './m-quest-stage-reward-delete-dialog.component.html'
})
export class MQuestStageRewardDeleteDialogComponent {
  mQuestStageReward: IMQuestStageReward;

  constructor(
    protected mQuestStageRewardService: MQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestStageRewardListModification',
        content: 'Deleted an mQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-stage-reward-delete-popup',
  template: ''
})
export class MQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestStageRewardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestStageReward = mQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-stage-reward', { outlets: { popup: null } }]);
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
