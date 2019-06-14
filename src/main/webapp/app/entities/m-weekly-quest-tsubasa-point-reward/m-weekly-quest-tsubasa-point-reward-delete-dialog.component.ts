import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';
import { MWeeklyQuestTsubasaPointRewardService } from './m-weekly-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-weekly-quest-tsubasa-point-reward-delete-dialog',
  templateUrl: './m-weekly-quest-tsubasa-point-reward-delete-dialog.component.html'
})
export class MWeeklyQuestTsubasaPointRewardDeleteDialogComponent {
  mWeeklyQuestTsubasaPointReward: IMWeeklyQuestTsubasaPointReward;

  constructor(
    protected mWeeklyQuestTsubasaPointRewardService: MWeeklyQuestTsubasaPointRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mWeeklyQuestTsubasaPointRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mWeeklyQuestTsubasaPointRewardListModification',
        content: 'Deleted an mWeeklyQuestTsubasaPointReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-weekly-quest-tsubasa-point-reward-delete-popup',
  template: ''
})
export class MWeeklyQuestTsubasaPointRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestTsubasaPointReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MWeeklyQuestTsubasaPointRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-weekly-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-weekly-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
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
