import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';
import { MCardIllustAssetsService } from './m-card-illust-assets.service';

@Component({
  selector: 'jhi-m-card-illust-assets-delete-dialog',
  templateUrl: './m-card-illust-assets-delete-dialog.component.html'
})
export class MCardIllustAssetsDeleteDialogComponent {
  mCardIllustAssets: IMCardIllustAssets;

  constructor(
    protected mCardIllustAssetsService: MCardIllustAssetsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCardIllustAssetsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCardIllustAssetsListModification',
        content: 'Deleted an mCardIllustAssets'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-card-illust-assets-delete-popup',
  template: ''
})
export class MCardIllustAssetsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardIllustAssets }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCardIllustAssetsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCardIllustAssets = mCardIllustAssets;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-card-illust-assets', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-card-illust-assets', { outlets: { popup: null } }]);
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
