import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';
import { MEventTitleEffectService } from './m-event-title-effect.service';

@Component({
  selector: 'jhi-m-event-title-effect-delete-dialog',
  templateUrl: './m-event-title-effect-delete-dialog.component.html'
})
export class MEventTitleEffectDeleteDialogComponent {
  mEventTitleEffect: IMEventTitleEffect;

  constructor(
    protected mEventTitleEffectService: MEventTitleEffectService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEventTitleEffectService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEventTitleEffectListModification',
        content: 'Deleted an mEventTitleEffect'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-event-title-effect-delete-popup',
  template: ''
})
export class MEventTitleEffectDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEventTitleEffect }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEventTitleEffectDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mEventTitleEffect = mEventTitleEffect;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-event-title-effect', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-event-title-effect', { outlets: { popup: null } }]);
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
