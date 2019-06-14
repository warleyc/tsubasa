import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';
import { MSceneTutorialMessageService } from './m-scene-tutorial-message.service';

@Component({
  selector: 'jhi-m-scene-tutorial-message-delete-dialog',
  templateUrl: './m-scene-tutorial-message-delete-dialog.component.html'
})
export class MSceneTutorialMessageDeleteDialogComponent {
  mSceneTutorialMessage: IMSceneTutorialMessage;

  constructor(
    protected mSceneTutorialMessageService: MSceneTutorialMessageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSceneTutorialMessageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSceneTutorialMessageListModification',
        content: 'Deleted an mSceneTutorialMessage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-scene-tutorial-message-delete-popup',
  template: ''
})
export class MSceneTutorialMessageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSceneTutorialMessage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSceneTutorialMessageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mSceneTutorialMessage = mSceneTutorialMessage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-scene-tutorial-message', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-scene-tutorial-message', { outlets: { popup: null } }]);
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
