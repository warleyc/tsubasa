import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';
import { MGuildTopBannerService } from './m-guild-top-banner.service';

@Component({
  selector: 'jhi-m-guild-top-banner-delete-dialog',
  templateUrl: './m-guild-top-banner-delete-dialog.component.html'
})
export class MGuildTopBannerDeleteDialogComponent {
  mGuildTopBanner: IMGuildTopBanner;

  constructor(
    protected mGuildTopBannerService: MGuildTopBannerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildTopBannerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildTopBannerListModification',
        content: 'Deleted an mGuildTopBanner'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-top-banner-delete-popup',
  template: ''
})
export class MGuildTopBannerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildTopBanner }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildTopBannerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGuildTopBanner = mGuildTopBanner;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-top-banner', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-top-banner', { outlets: { popup: null } }]);
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
