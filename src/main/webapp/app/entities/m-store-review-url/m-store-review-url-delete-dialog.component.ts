import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';
import { MStoreReviewUrlService } from './m-store-review-url.service';

@Component({
  selector: 'jhi-m-store-review-url-delete-dialog',
  templateUrl: './m-store-review-url-delete-dialog.component.html'
})
export class MStoreReviewUrlDeleteDialogComponent {
  mStoreReviewUrl: IMStoreReviewUrl;

  constructor(
    protected mStoreReviewUrlService: MStoreReviewUrlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mStoreReviewUrlService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mStoreReviewUrlListModification',
        content: 'Deleted an mStoreReviewUrl'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-store-review-url-delete-popup',
  template: ''
})
export class MStoreReviewUrlDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStoreReviewUrl }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MStoreReviewUrlDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mStoreReviewUrl = mStoreReviewUrl;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-store-review-url', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-store-review-url', { outlets: { popup: null } }]);
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
