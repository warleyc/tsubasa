import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLuck } from 'app/shared/model/m-luck.model';
import { MLuckService } from './m-luck.service';

@Component({
  selector: 'jhi-m-luck-delete-dialog',
  templateUrl: './m-luck-delete-dialog.component.html'
})
export class MLuckDeleteDialogComponent {
  mLuck: IMLuck;

  constructor(protected mLuckService: MLuckService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLuckService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLuckListModification',
        content: 'Deleted an mLuck'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-luck-delete-popup',
  template: ''
})
export class MLuckDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuck }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLuckDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLuck = mLuck;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-luck', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-luck', { outlets: { popup: null } }]);
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
