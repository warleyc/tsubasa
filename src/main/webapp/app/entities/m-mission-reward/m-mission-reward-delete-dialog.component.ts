import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMissionReward } from 'app/shared/model/m-mission-reward.model';
import { MMissionRewardService } from './m-mission-reward.service';

@Component({
  selector: 'jhi-m-mission-reward-delete-dialog',
  templateUrl: './m-mission-reward-delete-dialog.component.html'
})
export class MMissionRewardDeleteDialogComponent {
  mMissionReward: IMMissionReward;

  constructor(
    protected mMissionRewardService: MMissionRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMissionRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMissionRewardListModification',
        content: 'Deleted an mMissionReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-mission-reward-delete-popup',
  template: ''
})
export class MMissionRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMissionReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMissionRewardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMissionReward = mMissionReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-mission-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-mission-reward', { outlets: { popup: null } }]);
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
