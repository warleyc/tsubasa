import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';
import { MTimeattackRankingRewardGroupService } from './m-timeattack-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-group-delete-dialog',
  templateUrl: './m-timeattack-ranking-reward-group-delete-dialog.component.html'
})
export class MTimeattackRankingRewardGroupDeleteDialogComponent {
  mTimeattackRankingRewardGroup: IMTimeattackRankingRewardGroup;

  constructor(
    protected mTimeattackRankingRewardGroupService: MTimeattackRankingRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTimeattackRankingRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTimeattackRankingRewardGroupListModification',
        content: 'Deleted an mTimeattackRankingRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-group-delete-popup',
  template: ''
})
export class MTimeattackRankingRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackRankingRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTimeattackRankingRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTimeattackRankingRewardGroup = mTimeattackRankingRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-timeattack-ranking-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-timeattack-ranking-reward-group', { outlets: { popup: null } }]);
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
