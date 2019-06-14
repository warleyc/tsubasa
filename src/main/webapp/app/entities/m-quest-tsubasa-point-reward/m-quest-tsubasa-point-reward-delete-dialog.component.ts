import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';
import { MQuestTsubasaPointRewardService } from './m-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-quest-tsubasa-point-reward-delete-dialog',
  templateUrl: './m-quest-tsubasa-point-reward-delete-dialog.component.html'
})
export class MQuestTsubasaPointRewardDeleteDialogComponent {
  mQuestTsubasaPointReward: IMQuestTsubasaPointReward;

  constructor(
    protected mQuestTsubasaPointRewardService: MQuestTsubasaPointRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestTsubasaPointRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestTsubasaPointRewardListModification',
        content: 'Deleted an mQuestTsubasaPointReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-tsubasa-point-reward-delete-popup',
  template: ''
})
export class MQuestTsubasaPointRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestTsubasaPointReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestTsubasaPointRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestTsubasaPointReward = mQuestTsubasaPointReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
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
