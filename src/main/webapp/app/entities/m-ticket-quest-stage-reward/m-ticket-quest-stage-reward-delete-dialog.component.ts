import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';
import { MTicketQuestStageRewardService } from './m-ticket-quest-stage-reward.service';

@Component({
  selector: 'jhi-m-ticket-quest-stage-reward-delete-dialog',
  templateUrl: './m-ticket-quest-stage-reward-delete-dialog.component.html'
})
export class MTicketQuestStageRewardDeleteDialogComponent {
  mTicketQuestStageReward: IMTicketQuestStageReward;

  constructor(
    protected mTicketQuestStageRewardService: MTicketQuestStageRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTicketQuestStageRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTicketQuestStageRewardListModification',
        content: 'Deleted an mTicketQuestStageReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-ticket-quest-stage-reward-delete-popup',
  template: ''
})
export class MTicketQuestStageRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestStageReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTicketQuestStageRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTicketQuestStageReward = mTicketQuestStageReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-ticket-quest-stage-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-ticket-quest-stage-reward', { outlets: { popup: null } }]);
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
