import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';
import { MGuerillaQuestTsubasaPointRewardService } from './m-guerilla-quest-tsubasa-point-reward.service';

@Component({
  selector: 'jhi-m-guerilla-quest-tsubasa-point-reward-delete-dialog',
  templateUrl: './m-guerilla-quest-tsubasa-point-reward-delete-dialog.component.html'
})
export class MGuerillaQuestTsubasaPointRewardDeleteDialogComponent {
  mGuerillaQuestTsubasaPointReward: IMGuerillaQuestTsubasaPointReward;

  constructor(
    protected mGuerillaQuestTsubasaPointRewardService: MGuerillaQuestTsubasaPointRewardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuerillaQuestTsubasaPointRewardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuerillaQuestTsubasaPointRewardListModification',
        content: 'Deleted an mGuerillaQuestTsubasaPointReward'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guerilla-quest-tsubasa-point-reward-delete-popup',
  template: ''
})
export class MGuerillaQuestTsubasaPointRewardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestTsubasaPointReward }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuerillaQuestTsubasaPointRewardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointReward;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guerilla-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guerilla-quest-tsubasa-point-reward', { outlets: { popup: null } }]);
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
