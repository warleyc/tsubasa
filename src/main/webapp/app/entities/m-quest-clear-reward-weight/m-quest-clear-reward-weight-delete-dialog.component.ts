import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';
import { MQuestClearRewardWeightService } from './m-quest-clear-reward-weight.service';

@Component({
  selector: 'jhi-m-quest-clear-reward-weight-delete-dialog',
  templateUrl: './m-quest-clear-reward-weight-delete-dialog.component.html'
})
export class MQuestClearRewardWeightDeleteDialogComponent {
  mQuestClearRewardWeight: IMQuestClearRewardWeight;

  constructor(
    protected mQuestClearRewardWeightService: MQuestClearRewardWeightService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestClearRewardWeightService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestClearRewardWeightListModification',
        content: 'Deleted an mQuestClearRewardWeight'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-clear-reward-weight-delete-popup',
  template: ''
})
export class MQuestClearRewardWeightDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestClearRewardWeight }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestClearRewardWeightDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestClearRewardWeight = mQuestClearRewardWeight;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-clear-reward-weight', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-clear-reward-weight', { outlets: { popup: null } }]);
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
