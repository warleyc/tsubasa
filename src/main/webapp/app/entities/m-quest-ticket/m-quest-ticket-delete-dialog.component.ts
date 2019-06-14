import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestTicket } from 'app/shared/model/m-quest-ticket.model';
import { MQuestTicketService } from './m-quest-ticket.service';

@Component({
  selector: 'jhi-m-quest-ticket-delete-dialog',
  templateUrl: './m-quest-ticket-delete-dialog.component.html'
})
export class MQuestTicketDeleteDialogComponent {
  mQuestTicket: IMQuestTicket;

  constructor(
    protected mQuestTicketService: MQuestTicketService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestTicketService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestTicketListModification',
        content: 'Deleted an mQuestTicket'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-ticket-delete-popup',
  template: ''
})
export class MQuestTicketDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestTicket }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestTicketDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestTicket = mQuestTicket;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-ticket', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-ticket', { outlets: { popup: null } }]);
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
