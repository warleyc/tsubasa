import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';
import { MRegulatedLeagueRankingRewardService } from './m-regulated-league-ranking-reward.service';

@Component({
  selector: 'jhi-m-regulated-league-ranking-reward-delete-dialog',
  templateUrl: './m-regulated-league-ranking-reward-delete-dialog.component.html'
})
export class MRegulatedLeagueRankingRewardDeleteDialogComponent {
  mRegulatedLeagueRankingReward: IMRegulatedLeagueRankingReward;

  constructor(
    protected mRegulatedLeagueRankingRewardService: MRegulatedLeagueRankingRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mRegulatedLeagueRankingRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mRegulatedLeagueRankingRewardListModification',
        content: 'Deleted an mRegulatedLeagueRankingReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-regulated-league-ranking-reward-delete-popup',
  template: ''
})
export class MRegulatedLeagueRankingRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRegulatedLeagueRankingReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MRegulatedLeagueRankingRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mRegulatedLeagueRankingReward = mRegulatedLeagueRankingReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-regulated-league-ranking-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-regulated-league-ranking-reward', { outlets: { popup: null } }]);
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
