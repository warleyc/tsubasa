import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';
import { MGuildChatDefaultStampService } from './m-guild-chat-default-stamp.service';

@Component({
  selector: 'jhi-m-guild-chat-default-stamp-delete-dialog',
  templateUrl: './m-guild-chat-default-stamp-delete-dialog.component.html'
})
export class MGuildChatDefaultStampDeleteDialogComponent {
  mGuildChatDefaultStamp: IMGuildChatDefaultStamp;

  constructor(
    protected mGuildChatDefaultStampService: MGuildChatDefaultStampService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildChatDefaultStampService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildChatDefaultStampListModification',
        content: 'Deleted an mGuildChatDefaultStamp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-chat-default-stamp-delete-popup',
  template: ''
})
export class MGuildChatDefaultStampDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildChatDefaultStamp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildChatDefaultStampDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGuildChatDefaultStamp = mGuildChatDefaultStamp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-chat-default-stamp', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-chat-default-stamp', { outlets: { popup: null } }]);
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
