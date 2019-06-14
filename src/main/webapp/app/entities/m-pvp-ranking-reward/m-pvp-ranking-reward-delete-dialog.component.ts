import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';
import { MPvpRankingRewardService } from './m-pvp-ranking-reward.service';

@Component({
  selector: 'jhi-m-pvp-ranking-reward-delete-dialog',
  templateUrl: './m-pvp-ranking-reward-delete-dialog.component.html'
})
export class MPvpRankingRewardDeleteDialogComponent {
  mPvpRankingReward: IMPvpRankingReward;

  constructor(
    protected mPvpRankingRewardService: MPvpRankingRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpRankingRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpRankingRewardListModification',
        content: 'Deleted an mPvpRankingReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-ranking-reward-delete-popup',
  template: ''
})
export class MPvpRankingRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpRankingReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpRankingRewardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPvpRankingReward = mPvpRankingReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-ranking-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-ranking-reward', { outlets: { popup: null } }]);
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
