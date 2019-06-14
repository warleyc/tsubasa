import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSellCardCoin } from 'app/shared/model/m-sell-card-coin.model';
import { MSellCardCoinService } from './m-sell-card-coin.service';

@Component({
  selector: 'jhi-m-sell-card-coin-delete-dialog',
  templateUrl: './m-sell-card-coin-delete-dialog.component.html'
})
export class MSellCardCoinDeleteDialogComponent {
  mSellCardCoin: IMSellCardCoin;

  constructor(
    protected mSellCardCoinService: MSellCardCoinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSellCardCoinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSellCardCoinListModification',
        content: 'Deleted an mSellCardCoin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-sell-card-coin-delete-popup',
  template: ''
})
export class MSellCardCoinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSellCardCoin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSellCardCoinDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mSellCardCoin = mSellCardCoin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-sell-card-coin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-sell-card-coin', { outlets: { popup: null } }]);
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
