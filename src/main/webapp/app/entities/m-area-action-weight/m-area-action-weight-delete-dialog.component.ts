import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';
import { MAreaActionWeightService } from './m-area-action-weight.service';

@Component({
  selector: 'jhi-m-area-action-weight-delete-dialog',
  templateUrl: './m-area-action-weight-delete-dialog.component.html'
})
export class MAreaActionWeightDeleteDialogComponent {
  mAreaActionWeight: IMAreaActionWeight;

  constructor(
    protected mAreaActionWeightService: MAreaActionWeightService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAreaActionWeightService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAreaActionWeightListModification',
        content: 'Deleted an mAreaActionWeight'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-area-action-weight-delete-popup',
  template: ''
})
export class MAreaActionWeightDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAreaActionWeight }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAreaActionWeightDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAreaActionWeight = mAreaActionWeight;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-area-action-weight', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-area-action-weight', { outlets: { popup: null } }]);
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
