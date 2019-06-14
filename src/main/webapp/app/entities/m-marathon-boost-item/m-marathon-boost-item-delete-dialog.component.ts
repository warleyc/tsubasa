import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonBoostItem } from 'app/shared/model/m-marathon-boost-item.model';
import { MMarathonBoostItemService } from './m-marathon-boost-item.service';

@Component({
  selector: 'jhi-m-marathon-boost-item-delete-dialog',
  templateUrl: './m-marathon-boost-item-delete-dialog.component.html'
})
export class MMarathonBoostItemDeleteDialogComponent {
  mMarathonBoostItem: IMMarathonBoostItem;

  constructor(
    protected mMarathonBoostItemService: MMarathonBoostItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonBoostItemService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonBoostItemListModification',
        content: 'Deleted an mMarathonBoostItem'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-boost-item-delete-popup',
  template: ''
})
export class MMarathonBoostItemDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonBoostItem }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonBoostItemDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMarathonBoostItem = mMarathonBoostItem;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-boost-item', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-boost-item', { outlets: { popup: null } }]);
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
