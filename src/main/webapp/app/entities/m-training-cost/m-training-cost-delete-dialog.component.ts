import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTrainingCost } from 'app/shared/model/m-training-cost.model';
import { MTrainingCostService } from './m-training-cost.service';

@Component({
  selector: 'jhi-m-training-cost-delete-dialog',
  templateUrl: './m-training-cost-delete-dialog.component.html'
})
export class MTrainingCostDeleteDialogComponent {
  mTrainingCost: IMTrainingCost;

  constructor(
    protected mTrainingCostService: MTrainingCostService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTrainingCostService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTrainingCostListModification',
        content: 'Deleted an mTrainingCost'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-training-cost-delete-popup',
  template: ''
})
export class MTrainingCostDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTrainingCost }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTrainingCostDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTrainingCost = mTrainingCost;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-training-cost', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-training-cost', { outlets: { popup: null } }]);
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
