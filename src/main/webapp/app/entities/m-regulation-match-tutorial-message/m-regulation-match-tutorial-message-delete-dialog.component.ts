import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';
import { MRegulationMatchTutorialMessageService } from './m-regulation-match-tutorial-message.service';

@Component({
  selector: 'jhi-m-regulation-match-tutorial-message-delete-dialog',
  templateUrl: './m-regulation-match-tutorial-message-delete-dialog.component.html'
})
export class MRegulationMatchTutorialMessageDeleteDialogComponent {
  mRegulationMatchTutorialMessage: IMRegulationMatchTutorialMessage;

  constructor(
    protected mRegulationMatchTutorialMessageService: MRegulationMatchTutorialMessageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mRegulationMatchTutorialMessageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mRegulationMatchTutorialMessageListModification',
        content: 'Deleted an mRegulationMatchTutorialMessage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-regulation-match-tutorial-message-delete-popup',
  template: ''
})
export class MRegulationMatchTutorialMessageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRegulationMatchTutorialMessage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MRegulationMatchTutorialMessageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mRegulationMatchTutorialMessage = mRegulationMatchTutorialMessage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-regulation-match-tutorial-message', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-regulation-match-tutorial-message', { outlets: { popup: null } }]);
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
