import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';
import { MMarathonLoopRewardGroupService } from './m-marathon-loop-reward-group.service';

@Component({
  selector: 'jhi-m-marathon-loop-reward-group-delete-dialog',
  templateUrl: './m-marathon-loop-reward-group-delete-dialog.component.html'
})
export class MMarathonLoopRewardGroupDeleteDialogComponent {
  mMarathonLoopRewardGroup: IMMarathonLoopRewardGroup;

  constructor(
    protected mMarathonLoopRewardGroupService: MMarathonLoopRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonLoopRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonLoopRewardGroupListModification',
        content: 'Deleted an mMarathonLoopRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-loop-reward-group-delete-popup',
  template: ''
})
export class MMarathonLoopRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonLoopRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonLoopRewardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonLoopRewardGroup = mMarathonLoopRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-loop-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-loop-reward-group', { outlets: { popup: null } }]);
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
