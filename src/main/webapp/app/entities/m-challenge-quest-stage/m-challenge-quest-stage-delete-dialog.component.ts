import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';
import { MChallengeQuestStageService } from './m-challenge-quest-stage.service';

@Component({
  selector: 'jhi-m-challenge-quest-stage-delete-dialog',
  templateUrl: './m-challenge-quest-stage-delete-dialog.component.html'
})
export class MChallengeQuestStageDeleteDialogComponent {
  mChallengeQuestStage: IMChallengeQuestStage;

  constructor(
    protected mChallengeQuestStageService: MChallengeQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mChallengeQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mChallengeQuestStageListModification',
        content: 'Deleted an mChallengeQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-challenge-quest-stage-delete-popup',
  template: ''
})
export class MChallengeQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MChallengeQuestStageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mChallengeQuestStage = mChallengeQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-challenge-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-challenge-quest-stage', { outlets: { popup: null } }]);
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
