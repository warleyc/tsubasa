import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';
import { MGuerillaQuestStageRewardService } from './m-guerilla-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-guerilla-quest-stage-reward-delete-dialog',
  templateUrl: './m-guerilla-quest-stage-reward-delete-dialog.component.html'
})
export class MGuerillaQuestStageRewardDeleteDialogComponent {
  mGuerillaQuestStageReward: IMGuerillaQuestStageReward;

  constructor(
    protected mGuerillaQuestStageRewardService: MGuerillaQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuerillaQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuerillaQuestStageRewardListModification',
        content: 'Deleted an mGuerillaQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guerilla-quest-stage-reward-delete-popup',
  template: ''
})
export class MGuerillaQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuerillaQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGuerillaQuestStageReward = mGuerillaQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guerilla-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guerilla-quest-stage-reward', { outlets: { popup: null } }]);
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
