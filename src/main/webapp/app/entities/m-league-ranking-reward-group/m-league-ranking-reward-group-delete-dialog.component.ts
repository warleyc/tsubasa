import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';
import { MLeagueRankingRewardGroupService } from './m-league-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-league-ranking-reward-group-delete-dialog',
  templateUrl: './m-league-ranking-reward-group-delete-dialog.component.html'
})
export class MLeagueRankingRewardGroupDeleteDialogComponent {
  mLeagueRankingRewardGroup: IMLeagueRankingRewardGroup;

  constructor(
    protected mLeagueRankingRewardGroupService: MLeagueRankingRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLeagueRankingRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLeagueRankingRewardGroupListModification',
        content: 'Deleted an mLeagueRankingRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-league-ranking-reward-group-delete-popup',
  template: ''
})
export class MLeagueRankingRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueRankingRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLeagueRankingRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLeagueRankingRewardGroup = mLeagueRankingRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-league-ranking-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-league-ranking-reward-group', { outlets: { popup: null } }]);
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
