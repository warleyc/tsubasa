import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLuckWeeklyQuestStage } from 'app/shared/model/m-luck-weekly-quest-stage.model';
import { MLuckWeeklyQuestStageService } from './m-luck-weekly-quest-stage.service';

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-delete-dialog',
  templateUrl: './m-luck-weekly-quest-stage-delete-dialog.component.html'
})
export class MLuckWeeklyQuestStageDeleteDialogComponent {
  mLuckWeeklyQuestStage: IMLuckWeeklyQuestStage;

  constructor(
    protected mLuckWeeklyQuestStageService: MLuckWeeklyQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLuckWeeklyQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLuckWeeklyQuestStageListModification',
        content: 'Deleted an mLuckWeeklyQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-delete-popup',
  template: ''
})
export class MLuckWeeklyQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLuckWeeklyQuestStageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLuckWeeklyQuestStage = mLuckWeeklyQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-luck-weekly-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-luck-weekly-quest-stage', { outlets: { popup: null } }]);
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
