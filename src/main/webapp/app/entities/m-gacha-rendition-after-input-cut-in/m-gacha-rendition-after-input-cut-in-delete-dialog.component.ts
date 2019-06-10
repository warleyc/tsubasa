import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';
import { MGachaRenditionAfterInputCutInService } from './m-gacha-rendition-after-input-cut-in.service';

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-delete-dialog',
  templateUrl: './m-gacha-rendition-after-input-cut-in-delete-dialog.component.html'
})
export class MGachaRenditionAfterInputCutInDeleteDialogComponent {
  mGachaRenditionAfterInputCutIn: IMGachaRenditionAfterInputCutIn;

  constructor(
    protected mGachaRenditionAfterInputCutInService: MGachaRenditionAfterInputCutInService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionAfterInputCutInService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionAfterInputCutInListModification',
        content: 'Deleted an mGachaRenditionAfterInputCutIn'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-delete-popup',
  template: ''
})
export class MGachaRenditionAfterInputCutInDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionAfterInputCutIn }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionAfterInputCutInDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutIn;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-after-input-cut-in', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-after-input-cut-in', { outlets: { popup: null } }]);
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
