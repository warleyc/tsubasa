import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';
import { MPassiveEffectRangeService } from './m-passive-effect-range.service';

@Component({
  selector: 'jhi-m-passive-effect-range-delete-dialog',
  templateUrl: './m-passive-effect-range-delete-dialog.component.html'
})
export class MPassiveEffectRangeDeleteDialogComponent {
  mPassiveEffectRange: IMPassiveEffectRange;

  constructor(
    protected mPassiveEffectRangeService: MPassiveEffectRangeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPassiveEffectRangeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPassiveEffectRangeListModification',
        content: 'Deleted an mPassiveEffectRange'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-passive-effect-range-delete-popup',
  template: ''
})
export class MPassiveEffectRangeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPassiveEffectRange }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPassiveEffectRangeDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mPassiveEffectRange = mPassiveEffectRange;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-passive-effect-range', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-passive-effect-range', { outlets: { popup: null } }]);
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
