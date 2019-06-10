import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMArousal } from 'app/shared/model/m-arousal.model';
import { MArousalService } from './m-arousal.service';

@Component({
  selector: 'jhi-m-arousal-delete-dialog',
  templateUrl: './m-arousal-delete-dialog.component.html'
})
export class MArousalDeleteDialogComponent {
  mArousal: IMArousal;

  constructor(protected mArousalService: MArousalService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mArousalService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mArousalListModification',
        content: 'Deleted an mArousal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-arousal-delete-popup',
  template: ''
})
export class MArousalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mArousal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MArousalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mArousal = mArousal;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-arousal', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-arousal', { outlets: { popup: null } }]);
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
