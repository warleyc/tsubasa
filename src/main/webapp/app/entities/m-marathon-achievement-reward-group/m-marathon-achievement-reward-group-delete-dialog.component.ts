import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';
import { MMarathonAchievementRewardGroupService } from './m-marathon-achievement-reward-group.service';

@Component({
  selector: 'jhi-m-marathon-achievement-reward-group-delete-dialog',
  templateUrl: './m-marathon-achievement-reward-group-delete-dialog.component.html'
})
export class MMarathonAchievementRewardGroupDeleteDialogComponent {
  mMarathonAchievementRewardGroup: IMMarathonAchievementRewardGroup;

  constructor(
    protected mMarathonAchievementRewardGroupService: MMarathonAchievementRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonAchievementRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonAchievementRewardGroupListModification',
        content: 'Deleted an mMarathonAchievementRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-achievement-reward-group-delete-popup',
  template: ''
})
export class MMarathonAchievementRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonAchievementRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonAchievementRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonAchievementRewardGroup = mMarathonAchievementRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-achievement-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-achievement-reward-group', { outlets: { popup: null } }]);
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
