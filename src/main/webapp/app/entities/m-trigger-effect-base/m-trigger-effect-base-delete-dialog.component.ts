import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';
import { MTriggerEffectBaseService } from './m-trigger-effect-base.service';

@Component({
  selector: 'jhi-m-trigger-effect-base-delete-dialog',
  templateUrl: './m-trigger-effect-base-delete-dialog.component.html'
})
export class MTriggerEffectBaseDeleteDialogComponent {
  mTriggerEffectBase: IMTriggerEffectBase;

  constructor(
    protected mTriggerEffectBaseService: MTriggerEffectBaseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTriggerEffectBaseService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTriggerEffectBaseListModification',
        content: 'Deleted an mTriggerEffectBase'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-trigger-effect-base-delete-popup',
  template: ''
})
export class MTriggerEffectBaseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTriggerEffectBase }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTriggerEffectBaseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTriggerEffectBase = mTriggerEffectBase;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-trigger-effect-base', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-trigger-effect-base', { outlets: { popup: null } }]);
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
