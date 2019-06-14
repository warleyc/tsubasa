import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaTicket } from 'app/shared/model/m-gacha-ticket.model';
import { MGachaTicketService } from './m-gacha-ticket.service';

@Component({
  selector: 'jhi-m-gacha-ticket-delete-dialog',
  templateUrl: './m-gacha-ticket-delete-dialog.component.html'
})
export class MGachaTicketDeleteDialogComponent {
  mGachaTicket: IMGachaTicket;

  constructor(
    protected mGachaTicketService: MGachaTicketService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaTicketService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaTicketListModification',
        content: 'Deleted an mGachaTicket'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-ticket-delete-popup',
  template: ''
})
export class MGachaTicketDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaTicket }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaTicketDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGachaTicket = mGachaTicket;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-ticket', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-ticket', { outlets: { popup: null } }]);
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
