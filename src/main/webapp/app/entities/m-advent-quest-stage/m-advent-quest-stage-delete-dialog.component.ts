import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAdventQuestStage } from 'app/shared/model/m-advent-quest-stage.model';
import { MAdventQuestStageService } from './m-advent-quest-stage.service';

@Component({
  selector: 'jhi-m-advent-quest-stage-delete-dialog',
  templateUrl: './m-advent-quest-stage-delete-dialog.component.html'
})
export class MAdventQuestStageDeleteDialogComponent {
  mAdventQuestStage: IMAdventQuestStage;

  constructor(
    protected mAdventQuestStageService: MAdventQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAdventQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAdventQuestStageListModification',
        content: 'Deleted an mAdventQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-advent-quest-stage-delete-popup',
  template: ''
})
export class MAdventQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAdventQuestStageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAdventQuestStage = mAdventQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-advent-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-advent-quest-stage', { outlets: { popup: null } }]);
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
