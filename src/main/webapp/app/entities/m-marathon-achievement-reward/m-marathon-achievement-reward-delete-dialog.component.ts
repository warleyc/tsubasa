import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';
import { MMarathonAchievementRewardService } from './m-marathon-achievement-reward.service';

@Component({
  selector: 'jhi-m-marathon-achievement-reward-delete-dialog',
  templateUrl: './m-marathon-achievement-reward-delete-dialog.component.html'
})
export class MMarathonAchievementRewardDeleteDialogComponent {
  mMarathonAchievementReward: IMMarathonAchievementReward;

  constructor(
    protected mMarathonAchievementRewardService: MMarathonAchievementRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonAchievementRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonAchievementRewardListModification',
        content: 'Deleted an mMarathonAchievementReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-achievement-reward-delete-popup',
  template: ''
})
export class MMarathonAchievementRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonAchievementReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonAchievementRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonAchievementReward = mMarathonAchievementReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-achievement-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-achievement-reward', { outlets: { popup: null } }]);
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
