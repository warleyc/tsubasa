import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionSwipeIcon } from 'app/shared/model/m-gacha-rendition-swipe-icon.model';
import { MGachaRenditionSwipeIconService } from './m-gacha-rendition-swipe-icon.service';

@Component({
  selector: 'jhi-m-gacha-rendition-swipe-icon-delete-dialog',
  templateUrl: './m-gacha-rendition-swipe-icon-delete-dialog.component.html'
})
export class MGachaRenditionSwipeIconDeleteDialogComponent {
  mGachaRenditionSwipeIcon: IMGachaRenditionSwipeIcon;

  constructor(
    protected mGachaRenditionSwipeIconService: MGachaRenditionSwipeIconService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionSwipeIconService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionSwipeIconListModification',
        content: 'Deleted an mGachaRenditionSwipeIcon'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-swipe-icon-delete-popup',
  template: ''
})
export class MGachaRenditionSwipeIconDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionSwipeIcon }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionSwipeIconDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionSwipeIcon = mGachaRenditionSwipeIcon;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-swipe-icon', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-swipe-icon', { outlets: { popup: null } }]);
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
