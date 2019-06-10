import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCommonBanner } from 'app/shared/model/m-common-banner.model';
import { MCommonBannerService } from './m-common-banner.service';

@Component({
  selector: 'jhi-m-common-banner-delete-dialog',
  templateUrl: './m-common-banner-delete-dialog.component.html'
})
export class MCommonBannerDeleteDialogComponent {
  mCommonBanner: IMCommonBanner;

  constructor(
    protected mCommonBannerService: MCommonBannerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCommonBannerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCommonBannerListModification',
        content: 'Deleted an mCommonBanner'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-common-banner-delete-popup',
  template: ''
})
export class MCommonBannerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCommonBanner }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCommonBannerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCommonBanner = mCommonBanner;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-common-banner', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-common-banner', { outlets: { popup: null } }]);
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
