import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';
import { MMarathonRankingRewardGroupService } from './m-marathon-ranking-reward-group.service';

@Component({
  selector: 'jhi-m-marathon-ranking-reward-group-delete-dialog',
  templateUrl: './m-marathon-ranking-reward-group-delete-dialog.component.html'
})
export class MMarathonRankingRewardGroupDeleteDialogComponent {
  mMarathonRankingRewardGroup: IMMarathonRankingRewardGroup;

  constructor(
    protected mMarathonRankingRewardGroupService: MMarathonRankingRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonRankingRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonRankingRewardGroupListModification',
        content: 'Deleted an mMarathonRankingRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-ranking-reward-group-delete-popup',
  template: ''
})
export class MMarathonRankingRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonRankingRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonRankingRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonRankingRewardGroup = mMarathonRankingRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-ranking-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-ranking-reward-group', { outlets: { popup: null } }]);
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
