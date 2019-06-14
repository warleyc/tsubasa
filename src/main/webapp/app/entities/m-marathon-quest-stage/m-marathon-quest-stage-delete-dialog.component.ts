import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonQuestStage } from 'app/shared/model/m-marathon-quest-stage.model';
import { MMarathonQuestStageService } from './m-marathon-quest-stage.service';

@Component({
  selector: 'jhi-m-marathon-quest-stage-delete-dialog',
  templateUrl: './m-marathon-quest-stage-delete-dialog.component.html'
})
export class MMarathonQuestStageDeleteDialogComponent {
  mMarathonQuestStage: IMMarathonQuestStage;

  constructor(
    protected mMarathonQuestStageService: MMarathonQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonQuestStageListModification',
        content: 'Deleted an mMarathonQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-quest-stage-delete-popup',
  template: ''
})
export class MMarathonQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonQuestStageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonQuestStage = mMarathonQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-quest-stage', { outlets: { popup: null } }]);
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
