import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMHomeBanner } from 'app/shared/model/m-home-banner.model';
import { MHomeBannerService } from './m-home-banner.service';

@Component({
  selector: 'jhi-m-home-banner-delete-dialog',
  templateUrl: './m-home-banner-delete-dialog.component.html'
})
export class MHomeBannerDeleteDialogComponent {
  mHomeBanner: IMHomeBanner;

  constructor(
    protected mHomeBannerService: MHomeBannerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mHomeBannerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mHomeBannerListModification',
        content: 'Deleted an mHomeBanner'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-home-banner-delete-popup',
  template: ''
})
export class MHomeBannerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mHomeBanner }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MHomeBannerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mHomeBanner = mHomeBanner;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-home-banner', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-home-banner', { outlets: { popup: null } }]);
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
