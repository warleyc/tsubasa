import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMWeeklyQuestStage } from 'app/shared/model/m-weekly-quest-stage.model';
import { MWeeklyQuestStageService } from './m-weekly-quest-stage.service';

@Component({
  selector: 'jhi-m-weekly-quest-stage-delete-dialog',
  templateUrl: './m-weekly-quest-stage-delete-dialog.component.html'
})
export class MWeeklyQuestStageDeleteDialogComponent {
  mWeeklyQuestStage: IMWeeklyQuestStage;

  constructor(
    protected mWeeklyQuestStageService: MWeeklyQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mWeeklyQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mWeeklyQuestStageListModification',
        content: 'Deleted an mWeeklyQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-weekly-quest-stage-delete-popup',
  template: ''
})
export class MWeeklyQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MWeeklyQuestStageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mWeeklyQuestStage = mWeeklyQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-weekly-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-weekly-quest-stage', { outlets: { popup: null } }]);
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
