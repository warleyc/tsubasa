import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonRankingReward } from 'app/shared/model/m-marathon-ranking-reward.model';
import { MMarathonRankingRewardService } from './m-marathon-ranking-reward.service';

@Component({
  selector: 'jhi-m-marathon-ranking-reward-delete-dialog',
  templateUrl: './m-marathon-ranking-reward-delete-dialog.component.html'
})
export class MMarathonRankingRewardDeleteDialogComponent {
  mMarathonRankingReward: IMMarathonRankingReward;

  constructor(
    protected mMarathonRankingRewardService: MMarathonRankingRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonRankingRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonRankingRewardListModification',
        content: 'Deleted an mMarathonRankingReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-ranking-reward-delete-popup',
  template: ''
})
export class MMarathonRankingRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonRankingReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonRankingRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonRankingReward = mMarathonRankingReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-ranking-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-ranking-reward', { outlets: { popup: null } }]);
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
