import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRendition } from 'app/shared/model/m-gacha-rendition.model';
import { MGachaRenditionService } from './m-gacha-rendition.service';

@Component({
  selector: 'jhi-m-gacha-rendition-delete-dialog',
  templateUrl: './m-gacha-rendition-delete-dialog.component.html'
})
export class MGachaRenditionDeleteDialogComponent {
  mGachaRendition: IMGachaRendition;

  constructor(
    protected mGachaRenditionService: MGachaRenditionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionListModification',
        content: 'Deleted an mGachaRendition'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-delete-popup',
  template: ''
})
export class MGachaRenditionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRendition }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGachaRendition = mGachaRendition;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition', { outlets: { popup: null } }]);
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
