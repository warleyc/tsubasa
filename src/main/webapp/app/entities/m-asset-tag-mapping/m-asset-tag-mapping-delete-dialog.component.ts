import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';
import { MAssetTagMappingService } from './m-asset-tag-mapping.service';

@Component({
  selector: 'jhi-m-asset-tag-mapping-delete-dialog',
  templateUrl: './m-asset-tag-mapping-delete-dialog.component.html'
})
export class MAssetTagMappingDeleteDialogComponent {
  mAssetTagMapping: IMAssetTagMapping;

  constructor(
    protected mAssetTagMappingService: MAssetTagMappingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAssetTagMappingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAssetTagMappingListModification',
        content: 'Deleted an mAssetTagMapping'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-asset-tag-mapping-delete-popup',
  template: ''
})
export class MAssetTagMappingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAssetTagMapping }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAssetTagMappingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAssetTagMapping = mAssetTagMapping;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-asset-tag-mapping', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-asset-tag-mapping', { outlets: { popup: null } }]);
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
