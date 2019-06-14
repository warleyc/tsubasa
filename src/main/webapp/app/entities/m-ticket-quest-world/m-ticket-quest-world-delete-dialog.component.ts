import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';
import { MTicketQuestWorldService } from './m-ticket-quest-world.service';

@Component({
  selector: 'jhi-m-ticket-quest-world-delete-dialog',
  templateUrl: './m-ticket-quest-world-delete-dialog.component.html'
})
export class MTicketQuestWorldDeleteDialogComponent {
  mTicketQuestWorld: IMTicketQuestWorld;

  constructor(
    protected mTicketQuestWorldService: MTicketQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTicketQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTicketQuestWorldListModification',
        content: 'Deleted an mTicketQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-ticket-quest-world-delete-popup',
  template: ''
})
export class MTicketQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTicketQuestWorldDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTicketQuestWorld = mTicketQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-ticket-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-ticket-quest-world', { outlets: { popup: null } }]);
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
