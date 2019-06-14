import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestStage } from 'app/shared/model/m-quest-stage.model';
import { MQuestStageService } from './m-quest-stage.service';

@Component({
  selector: 'jhi-m-quest-stage-delete-dialog',
  templateUrl: './m-quest-stage-delete-dialog.component.html'
})
export class MQuestStageDeleteDialogComponent {
  mQuestStage: IMQuestStage;

  constructor(
    protected mQuestStageService: MQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestStageListModification',
        content: 'Deleted an mQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-stage-delete-popup',
  template: ''
})
export class MQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestStageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestStage = mQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-stage', { outlets: { popup: null } }]);
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
