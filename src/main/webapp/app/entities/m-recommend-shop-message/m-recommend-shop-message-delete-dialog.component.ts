import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMRecommendShopMessage } from 'app/shared/model/m-recommend-shop-message.model';
import { MRecommendShopMessageService } from './m-recommend-shop-message.service';

@Component({
  selector: 'jhi-m-recommend-shop-message-delete-dialog',
  templateUrl: './m-recommend-shop-message-delete-dialog.component.html'
})
export class MRecommendShopMessageDeleteDialogComponent {
  mRecommendShopMessage: IMRecommendShopMessage;

  constructor(
    protected mRecommendShopMessageService: MRecommendShopMessageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mRecommendShopMessageService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mRecommendShopMessageListModification',
        content: 'Deleted an mRecommendShopMessage'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-recommend-shop-message-delete-popup',
  template: ''
})
export class MRecommendShopMessageDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRecommendShopMessage }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MRecommendShopMessageDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mRecommendShopMessage = mRecommendShopMessage;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-recommend-shop-message', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-recommend-shop-message', { outlets: { popup: null } }]);
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
