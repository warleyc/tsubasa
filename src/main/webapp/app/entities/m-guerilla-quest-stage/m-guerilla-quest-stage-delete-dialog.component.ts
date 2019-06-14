import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuerillaQuestStage } from 'app/shared/model/m-guerilla-quest-stage.model';
import { MGuerillaQuestStageService } from './m-guerilla-quest-stage.service';

@Component({
  selector: 'jhi-m-guerilla-quest-stage-delete-dialog',
  templateUrl: './m-guerilla-quest-stage-delete-dialog.component.html'
})
export class MGuerillaQuestStageDeleteDialogComponent {
  mGuerillaQuestStage: IMGuerillaQuestStage;

  constructor(
    protected mGuerillaQuestStageService: MGuerillaQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuerillaQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuerillaQuestStageListModification',
        content: 'Deleted an mGuerillaQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guerilla-quest-stage-delete-popup',
  template: ''
})
export class MGuerillaQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuerillaQuestStageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGuerillaQuestStage = mGuerillaQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guerilla-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guerilla-quest-stage', { outlets: { popup: null } }]);
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
