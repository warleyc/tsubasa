import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';
import { MLeagueRankingRewardService } from './m-league-ranking-reward.service';

@Component({
  selector: 'jhi-m-league-ranking-reward-delete-dialog',
  templateUrl: './m-league-ranking-reward-delete-dialog.component.html'
})
export class MLeagueRankingRewardDeleteDialogComponent {
  mLeagueRankingReward: IMLeagueRankingReward;

  constructor(
    protected mLeagueRankingRewardService: MLeagueRankingRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLeagueRankingRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLeagueRankingRewardListModification',
        content: 'Deleted an mLeagueRankingReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-league-ranking-reward-delete-popup',
  template: ''
})
export class MLeagueRankingRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueRankingReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLeagueRankingRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLeagueRankingReward = mLeagueRankingReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-league-ranking-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-league-ranking-reward', { outlets: { popup: null } }]);
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
