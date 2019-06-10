import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';
import { MApRecoveryItemService } from './m-ap-recovery-item.service';

@Component({
  selector: 'jhi-m-ap-recovery-item-delete-dialog',
  templateUrl: './m-ap-recovery-item-delete-dialog.component.html'
})
export class MApRecoveryItemDeleteDialogComponent {
  mApRecoveryItem: IMApRecoveryItem;

  constructor(
    protected mApRecoveryItemService: MApRecoveryItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mApRecoveryItemService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mApRecoveryItemListModification',
        content: 'Deleted an mApRecoveryItem'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-ap-recovery-item-delete-popup',
  template: ''
})
export class MApRecoveryItemDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mApRecoveryItem }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MApRecoveryItemDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mApRecoveryItem = mApRecoveryItem;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-ap-recovery-item', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-ap-recovery-item', { outlets: { popup: null } }]);
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
