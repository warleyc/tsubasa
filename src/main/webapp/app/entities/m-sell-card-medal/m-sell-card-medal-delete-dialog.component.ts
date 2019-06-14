import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSellCardMedal } from 'app/shared/model/m-sell-card-medal.model';
import { MSellCardMedalService } from './m-sell-card-medal.service';

@Component({
  selector: 'jhi-m-sell-card-medal-delete-dialog',
  templateUrl: './m-sell-card-medal-delete-dialog.component.html'
})
export class MSellCardMedalDeleteDialogComponent {
  mSellCardMedal: IMSellCardMedal;

  constructor(
    protected mSellCardMedalService: MSellCardMedalService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSellCardMedalService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSellCardMedalListModification',
        content: 'Deleted an mSellCardMedal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-sell-card-medal-delete-popup',
  template: ''
})
export class MSellCardMedalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSellCardMedal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSellCardMedalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mSellCardMedal = mSellCardMedal;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-sell-card-medal', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-sell-card-medal', { outlets: { popup: null } }]);
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
