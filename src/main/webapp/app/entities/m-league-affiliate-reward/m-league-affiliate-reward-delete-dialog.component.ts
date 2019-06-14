import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';
import { MLeagueAffiliateRewardService } from './m-league-affiliate-reward.service';

@Component({
  selector: 'jhi-m-league-affiliate-reward-delete-dialog',
  templateUrl: './m-league-affiliate-reward-delete-dialog.component.html'
})
export class MLeagueAffiliateRewardDeleteDialogComponent {
  mLeagueAffiliateReward: IMLeagueAffiliateReward;

  constructor(
    protected mLeagueAffiliateRewardService: MLeagueAffiliateRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLeagueAffiliateRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLeagueAffiliateRewardListModification',
        content: 'Deleted an mLeagueAffiliateReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-league-affiliate-reward-delete-popup',
  template: ''
})
export class MLeagueAffiliateRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueAffiliateReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLeagueAffiliateRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLeagueAffiliateReward = mLeagueAffiliateReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-league-affiliate-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-league-affiliate-reward', { outlets: { popup: null } }]);
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
