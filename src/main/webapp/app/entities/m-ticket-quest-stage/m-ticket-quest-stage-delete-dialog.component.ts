import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';
import { MTicketQuestStageService } from './m-ticket-quest-stage.service';

@Component({
  selector: 'jhi-m-ticket-quest-stage-delete-dialog',
  templateUrl: './m-ticket-quest-stage-delete-dialog.component.html'
})
export class MTicketQuestStageDeleteDialogComponent {
  mTicketQuestStage: IMTicketQuestStage;

  constructor(
    protected mTicketQuestStageService: MTicketQuestStageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTicketQuestStageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTicketQuestStageListModification',
        content: 'Deleted an mTicketQuestStage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-ticket-quest-stage-delete-popup',
  template: ''
})
export class MTicketQuestStageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestStage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTicketQuestStageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTicketQuestStage = mTicketQuestStage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-ticket-quest-stage', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-ticket-quest-stage', { outlets: { popup: null } }]);
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
