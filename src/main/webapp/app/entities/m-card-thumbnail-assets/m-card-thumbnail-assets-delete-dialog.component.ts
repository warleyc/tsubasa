import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';
import { MCardThumbnailAssetsService } from './m-card-thumbnail-assets.service';

@Component({
  selector: 'jhi-m-card-thumbnail-assets-delete-dialog',
  templateUrl: './m-card-thumbnail-assets-delete-dialog.component.html'
})
export class MCardThumbnailAssetsDeleteDialogComponent {
  mCardThumbnailAssets: IMCardThumbnailAssets;

  constructor(
    protected mCardThumbnailAssetsService: MCardThumbnailAssetsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCardThumbnailAssetsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCardThumbnailAssetsListModification',
        content: 'Deleted an mCardThumbnailAssets'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-card-thumbnail-assets-delete-popup',
  template: ''
})
export class MCardThumbnailAssetsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardThumbnailAssets }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCardThumbnailAssetsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mCardThumbnailAssets = mCardThumbnailAssets;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-card-thumbnail-assets', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-card-thumbnail-assets', { outlets: { popup: null } }]);
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
