import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMArousalItem } from 'app/shared/model/m-arousal-item.model';
import { MArousalItemService } from './m-arousal-item.service';

@Component({
  selector: 'jhi-m-arousal-item-delete-dialog',
  templateUrl: './m-arousal-item-delete-dialog.component.html'
})
export class MArousalItemDeleteDialogComponent {
  mArousalItem: IMArousalItem;

  constructor(
    protected mArousalItemService: MArousalItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mArousalItemService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mArousalItemListModification',
        content: 'Deleted an mArousalItem'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-arousal-item-delete-popup',
  template: ''
})
export class MArousalItemDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mArousalItem }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MArousalItemDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mArousalItem = mArousalItem;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-arousal-item', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-arousal-item', { outlets: { popup: null } }]);
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
