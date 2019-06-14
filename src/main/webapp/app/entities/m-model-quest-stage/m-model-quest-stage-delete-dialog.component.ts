import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMModelQuestStage } from 'app/shared/model/m-model-quest-stage.model';
import { MModelQuestStageService } from './m-model-quest-stage.service';

@Component({
  selector: 'jhi-m-model-quest-stage-delete-dialog',
  templateUrl: './m-model-quest-stage-delete-dialog.component.html'
})
export class MModelQuestStageDeleteDialogComponent {
  mModelQuestStage: IMModelQuestStage;

  constructor(
    protected mModelQuestStageService: MModelQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mModelQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mModelQuestStageListModification',
        content: 'Deleted an mModelQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-model-quest-stage-delete-popup',
  template: ''
})
export class MModelQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MModelQuestStageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mModelQuestStage = mModelQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-model-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-model-quest-stage', { outlets: { popup: null } }]);
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
