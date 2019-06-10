import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';
import { MGachaRenditionAfterInputCutInTextColorService } from './m-gacha-rendition-after-input-cut-in-text-color.service';

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-text-color-delete-dialog',
  templateUrl: './m-gacha-rendition-after-input-cut-in-text-color-delete-dialog.component.html'
})
export class MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent {
  mGachaRenditionAfterInputCutInTextColor: IMGachaRenditionAfterInputCutInTextColor;

  constructor(
    protected mGachaRenditionAfterInputCutInTextColorService: MGachaRenditionAfterInputCutInTextColorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionAfterInputCutInTextColorService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionAfterInputCutInTextColorListModification',
        content: 'Deleted an mGachaRenditionAfterInputCutInTextColor'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-text-color-delete-popup',
  template: ''
})
export class MGachaRenditionAfterInputCutInTextColorDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionAfterInputCutInTextColor }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionAfterInputCutInTextColorDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColor;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-after-input-cut-in-text-color', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-after-input-cut-in-text-color', { outlets: { popup: null } }]);
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
