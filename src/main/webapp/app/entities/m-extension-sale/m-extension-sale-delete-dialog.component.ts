import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMExtensionSale } from 'app/shared/model/m-extension-sale.model';
import { MExtensionSaleService } from './m-extension-sale.service';

@Component({
  selector: 'jhi-m-extension-sale-delete-dialog',
  templateUrl: './m-extension-sale-delete-dialog.component.html'
})
export class MExtensionSaleDeleteDialogComponent {
  mExtensionSale: IMExtensionSale;

  constructor(
    protected mExtensionSaleService: MExtensionSaleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mExtensionSaleService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mExtensionSaleListModification',
        content: 'Deleted an mExtensionSale'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-extension-sale-delete-popup',
  template: ''
})
export class MExtensionSaleDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mExtensionSale }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MExtensionSaleDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mExtensionSale = mExtensionSale;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-extension-sale', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-extension-sale', { outlets: { popup: null } }]);
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
