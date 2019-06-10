import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMChatSystemMessage } from 'app/shared/model/m-chat-system-message.model';
import { MChatSystemMessageService } from './m-chat-system-message.service';

@Component({
  selector: 'jhi-m-chat-system-message-delete-dialog',
  templateUrl: './m-chat-system-message-delete-dialog.component.html'
})
export class MChatSystemMessageDeleteDialogComponent {
  mChatSystemMessage: IMChatSystemMessage;

  constructor(
    protected mChatSystemMessageService: MChatSystemMessageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mChatSystemMessageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mChatSystemMessageListModification',
        content: 'Deleted an mChatSystemMessage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-chat-system-message-delete-popup',
  template: ''
})
export class MChatSystemMessageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChatSystemMessage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MChatSystemMessageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mChatSystemMessage = mChatSystemMessage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-chat-system-message', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-chat-system-message', { outlets: { popup: null } }]);
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
