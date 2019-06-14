import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonLoopReward } from 'app/shared/model/m-marathon-loop-reward.model';
import { MMarathonLoopRewardService } from './m-marathon-loop-reward.service';

@Component({
  selector: 'jhi-m-marathon-loop-reward-delete-dialog',
  templateUrl: './m-marathon-loop-reward-delete-dialog.component.html'
})
export class MMarathonLoopRewardDeleteDialogComponent {
  mMarathonLoopReward: IMMarathonLoopReward;

  constructor(
    protected mMarathonLoopRewardService: MMarathonLoopRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonLoopRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonLoopRewardListModification',
        content: 'Deleted an mMarathonLoopReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-loop-reward-delete-popup',
  template: ''
})
export class MMarathonLoopRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonLoopReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonLoopRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonLoopReward = mMarathonLoopReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-loop-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-loop-reward', { outlets: { popup: null } }]);
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
