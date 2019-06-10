import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';
import { MGachaRenditionExtraCutinService } from './m-gacha-rendition-extra-cutin.service';

@Component({
  selector: 'jhi-m-gacha-rendition-extra-cutin-delete-dialog',
  templateUrl: './m-gacha-rendition-extra-cutin-delete-dialog.component.html'
})
export class MGachaRenditionExtraCutinDeleteDialogComponent {
  mGachaRenditionExtraCutin: IMGachaRenditionExtraCutin;

  constructor(
    protected mGachaRenditionExtraCutinService: MGachaRenditionExtraCutinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionExtraCutinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionExtraCutinListModification',
        content: 'Deleted an mGachaRenditionExtraCutin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-extra-cutin-delete-popup',
  template: ''
})
export class MGachaRenditionExtraCutinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionExtraCutin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionExtraCutinDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionExtraCutin = mGachaRenditionExtraCutin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-extra-cutin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-extra-cutin', { outlets: { popup: null } }]);
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
