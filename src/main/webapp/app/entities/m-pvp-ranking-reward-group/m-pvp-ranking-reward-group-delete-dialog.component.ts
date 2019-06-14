import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';
import { MPvpRankingRewardGroupService } from './m-pvp-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-pvp-ranking-reward-group-delete-dialog',
  templateUrl: './m-pvp-ranking-reward-group-delete-dialog.component.html'
})
export class MPvpRankingRewardGroupDeleteDialogComponent {
  mPvpRankingRewardGroup: IMPvpRankingRewardGroup;

  constructor(
    protected mPvpRankingRewardGroupService: MPvpRankingRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpRankingRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpRankingRewardGroupListModification',
        content: 'Deleted an mPvpRankingRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-ranking-reward-group-delete-popup',
  template: ''
})
export class MPvpRankingRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpRankingRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpRankingRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mPvpRankingRewardGroup = mPvpRankingRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-ranking-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-ranking-reward-group', { outlets: { popup: null } }]);
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
