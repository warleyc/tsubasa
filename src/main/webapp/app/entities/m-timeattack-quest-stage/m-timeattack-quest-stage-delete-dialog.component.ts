import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTimeattackQuestStage } from 'app/shared/model/m-timeattack-quest-stage.model';
import { MTimeattackQuestStageService } from './m-timeattack-quest-stage.service';

@Component({
  selector: 'jhi-m-timeattack-quest-stage-delete-dialog',
  templateUrl: './m-timeattack-quest-stage-delete-dialog.component.html'
})
export class MTimeattackQuestStageDeleteDialogComponent {
  mTimeattackQuestStage: IMTimeattackQuestStage;

  constructor(
    protected mTimeattackQuestStageService: MTimeattackQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTimeattackQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTimeattackQuestStageListModification',
        content: 'Deleted an mTimeattackQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-timeattack-quest-stage-delete-popup',
  template: ''
})
export class MTimeattackQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTimeattackQuestStageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTimeattackQuestStage = mTimeattackQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-timeattack-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-timeattack-quest-stage', { outlets: { popup: null } }]);
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
