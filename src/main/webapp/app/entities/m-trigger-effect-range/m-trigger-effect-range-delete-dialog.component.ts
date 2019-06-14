import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';
import { MTriggerEffectRangeService } from './m-trigger-effect-range.service';

@Component({
  selector: 'jhi-m-trigger-effect-range-delete-dialog',
  templateUrl: './m-trigger-effect-range-delete-dialog.component.html'
})
export class MTriggerEffectRangeDeleteDialogComponent {
  mTriggerEffectRange: IMTriggerEffectRange;

  constructor(
    protected mTriggerEffectRangeService: MTriggerEffectRangeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTriggerEffectRangeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTriggerEffectRangeListModification',
        content: 'Deleted an mTriggerEffectRange'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-trigger-effect-range-delete-popup',
  template: ''
})
export class MTriggerEffectRangeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTriggerEffectRange }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTriggerEffectRangeDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTriggerEffectRange = mTriggerEffectRange;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-trigger-effect-range', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-trigger-effect-range', { outlets: { popup: null } }]);
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
