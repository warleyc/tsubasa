import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAsset } from 'app/shared/model/m-asset.model';
import { MAssetService } from './m-asset.service';

@Component({
  selector: 'jhi-m-asset-delete-dialog',
  templateUrl: './m-asset-delete-dialog.component.html'
})
export class MAssetDeleteDialogComponent {
  mAsset: IMAsset;

  constructor(protected mAssetService: MAssetService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAssetService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAssetListModification',
        content: 'Deleted an mAsset'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-asset-delete-popup',
  template: ''
})
export class MAssetDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAsset }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAssetDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAsset = mAsset;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-asset', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-asset', { outlets: { popup: null } }]);
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
