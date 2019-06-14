import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTutorialMessage } from 'app/shared/model/m-tutorial-message.model';
import { MTutorialMessageService } from './m-tutorial-message.service';

@Component({
  selector: 'jhi-m-tutorial-message-delete-dialog',
  templateUrl: './m-tutorial-message-delete-dialog.component.html'
})
export class MTutorialMessageDeleteDialogComponent {
  mTutorialMessage: IMTutorialMessage;

  constructor(
    protected mTutorialMessageService: MTutorialMessageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTutorialMessageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTutorialMessageListModification',
        content: 'Deleted an mTutorialMessage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-tutorial-message-delete-popup',
  template: ''
})
export class MTutorialMessageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTutorialMessage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTutorialMessageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTutorialMessage = mTutorialMessage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-tutorial-message', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-tutorial-message', { outlets: { popup: null } }]);
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
