import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';
import { MTimeattackRankingRewardService } from './m-timeattack-ranking-reward.service';

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-delete-dialog',
  templateUrl: './m-timeattack-ranking-reward-delete-dialog.component.html'
})
export class MTimeattackRankingRewardDeleteDialogComponent {
  mTimeattackRankingReward: IMTimeattackRankingReward;

  constructor(
    protected mTimeattackRankingRewardService: MTimeattackRankingRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTimeattackRankingRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTimeattackRankingRewardListModification',
        content: 'Deleted an mTimeattackRankingReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-delete-popup',
  template: ''
})
export class MTimeattackRankingRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackRankingReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTimeattackRankingRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTimeattackRankingReward = mTimeattackRankingReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-timeattack-ranking-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-timeattack-ranking-reward', { outlets: { popup: null } }]);
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
