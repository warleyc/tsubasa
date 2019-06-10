import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';
import { MAdventQuestTsubasaPointRewardService } from './m-advent-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-advent-quest-tsubasa-point-reward-delete-dialog',
  templateUrl: './m-advent-quest-tsubasa-point-reward-delete-dialog.component.html'
})
export class MAdventQuestTsubasaPointRewardDeleteDialogComponent {
  mAdventQuestTsubasaPointReward: IMAdventQuestTsubasaPointReward;

  constructor(
    protected mAdventQuestTsubasaPointRewardService: MAdventQuestTsubasaPointRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAdventQuestTsubasaPointRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAdventQuestTsubasaPointRewardListModification',
        content: 'Deleted an mAdventQuestTsubasaPointReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-advent-quest-tsubasa-point-reward-delete-popup',
  template: ''
})
export class MAdventQuestTsubasaPointRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestTsubasaPointReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAdventQuestTsubasaPointRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-advent-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-advent-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
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
