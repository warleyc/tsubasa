import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';
import { MGachaRenditionTradeSignService } from './m-gacha-rendition-trade-sign.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trade-sign-delete-dialog',
  templateUrl: './m-gacha-rendition-trade-sign-delete-dialog.component.html'
})
export class MGachaRenditionTradeSignDeleteDialogComponent {
  mGachaRenditionTradeSign: IMGachaRenditionTradeSign;

  constructor(
    protected mGachaRenditionTradeSignService: MGachaRenditionTradeSignService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionTradeSignService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionTradeSignListModification',
        content: 'Deleted an mGachaRenditionTradeSign'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-trade-sign-delete-popup',
  template: ''
})
export class MGachaRenditionTradeSignDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTradeSign }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionTradeSignDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionTradeSign = mGachaRenditionTradeSign;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-trade-sign', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-trade-sign', { outlets: { popup: null } }]);
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
