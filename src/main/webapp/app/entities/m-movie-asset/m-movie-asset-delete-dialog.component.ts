import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMovieAsset } from 'app/shared/model/m-movie-asset.model';
import { MMovieAssetService } from './m-movie-asset.service';

@Component({
  selector: 'jhi-m-movie-asset-delete-dialog',
  templateUrl: './m-movie-asset-delete-dialog.component.html'
})
export class MMovieAssetDeleteDialogComponent {
  mMovieAsset: IMMovieAsset;

  constructor(
    protected mMovieAssetService: MMovieAssetService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMovieAssetService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMovieAssetListModification',
        content: 'Deleted an mMovieAsset'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-movie-asset-delete-popup',
  template: ''
})
export class MMovieAssetDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMovieAsset }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMovieAssetDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMovieAsset = mMovieAsset;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-movie-asset', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-movie-asset', { outlets: { popup: null } }]);
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
