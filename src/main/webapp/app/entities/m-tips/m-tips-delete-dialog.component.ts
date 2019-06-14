import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTips } from 'app/shared/model/m-tips.model';
import { MTipsService } from './m-tips.service';

@Component({
  selector: 'jhi-m-tips-delete-dialog',
  templateUrl: './m-tips-delete-dialog.component.html'
})
export class MTipsDeleteDialogComponent {
  mTips: IMTips;

  constructor(protected mTipsService: MTipsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTipsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTipsListModification',
        content: 'Deleted an mTips'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-tips-delete-popup',
  template: ''
})
export class MTipsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTips }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTipsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTips = mTips;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-tips', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-tips', { outlets: { popup: null } }]);
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
