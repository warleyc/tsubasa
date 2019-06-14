import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';
import { MQuestSpecialRewardService } from './m-quest-special-reward.service';

@Component({
  selector: 'jhi-m-quest-special-reward-delete-dialog',
  templateUrl: './m-quest-special-reward-delete-dialog.component.html'
})
export class MQuestSpecialRewardDeleteDialogComponent {
  mQuestSpecialReward: IMQuestSpecialReward;

  constructor(
    protected mQuestSpecialRewardService: MQuestSpecialRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestSpecialRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestSpecialRewardListModification',
        content: 'Deleted an mQuestSpecialReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-special-reward-delete-popup',
  template: ''
})
export class MQuestSpecialRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestSpecialReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestSpecialRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestSpecialReward = mQuestSpecialReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-special-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-special-reward', { outlets: { popup: null } }]);
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
