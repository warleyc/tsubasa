import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';
import { MQuestCoopRewardService } from './m-quest-coop-reward.service';

@Component({
  selector: 'jhi-m-quest-coop-reward-delete-dialog',
  templateUrl: './m-quest-coop-reward-delete-dialog.component.html'
})
export class MQuestCoopRewardDeleteDialogComponent {
  mQuestCoopReward: IMQuestCoopReward;

  constructor(
    protected mQuestCoopRewardService: MQuestCoopRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestCoopRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestCoopRewardListModification',
        content: 'Deleted an mQuestCoopReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-coop-reward-delete-popup',
  template: ''
})
export class MQuestCoopRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestCoopReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestCoopRewardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestCoopReward = mQuestCoopReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-coop-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-coop-reward', { outlets: { popup: null } }]);
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
