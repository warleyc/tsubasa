import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';
import { MMarathonQuestTsubasaPointRewardService } from './m-marathon-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-marathon-quest-tsubasa-point-reward-delete-dialog',
  templateUrl: './m-marathon-quest-tsubasa-point-reward-delete-dialog.component.html'
})
export class MMarathonQuestTsubasaPointRewardDeleteDialogComponent {
  mMarathonQuestTsubasaPointReward: IMMarathonQuestTsubasaPointReward;

  constructor(
    protected mMarathonQuestTsubasaPointRewardService: MMarathonQuestTsubasaPointRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonQuestTsubasaPointRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonQuestTsubasaPointRewardListModification',
        content: 'Deleted an mMarathonQuestTsubasaPointReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-quest-tsubasa-point-reward-delete-popup',
  template: ''
})
export class MMarathonQuestTsubasaPointRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestTsubasaPointReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonQuestTsubasaPointRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
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
