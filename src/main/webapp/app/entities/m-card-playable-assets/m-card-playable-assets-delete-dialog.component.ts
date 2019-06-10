import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';
import { MCardPlayableAssetsService } from './m-card-playable-assets.service';

@Component({
  selector: 'jhi-m-card-playable-assets-delete-dialog',
  templateUrl: './m-card-playable-assets-delete-dialog.component.html'
})
export class MCardPlayableAssetsDeleteDialogComponent {
  mCardPlayableAssets: IMCardPlayableAssets;

  constructor(
    protected mCardPlayableAssetsService: MCardPlayableAssetsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCardPlayableAssetsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCardPlayableAssetsListModification',
        content: 'Deleted an mCardPlayableAssets'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-card-playable-assets-delete-popup',
  template: ''
})
export class MCardPlayableAssetsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardPlayableAssets }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCardPlayableAssetsDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mCardPlayableAssets = mCardPlayableAssets;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-card-playable-assets', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-card-playable-assets', { outlets: { popup: null } }]);
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
