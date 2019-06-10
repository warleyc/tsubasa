import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';
import { MDistributeCardParameterStepService } from './m-distribute-card-parameter-step.service';

@Component({
  selector: 'jhi-m-distribute-card-parameter-step-delete-dialog',
  templateUrl: './m-distribute-card-parameter-step-delete-dialog.component.html'
})
export class MDistributeCardParameterStepDeleteDialogComponent {
  mDistributeCardParameterStep: IMDistributeCardParameterStep;

  constructor(
    protected mDistributeCardParameterStepService: MDistributeCardParameterStepService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDistributeCardParameterStepService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDistributeCardParameterStepListModification',
        content: 'Deleted an mDistributeCardParameterStep'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-distribute-card-parameter-step-delete-popup',
  template: ''
})
export class MDistributeCardParameterStepDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDistributeCardParameterStep }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDistributeCardParameterStepDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mDistributeCardParameterStep = mDistributeCardParameterStep;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-distribute-card-parameter-step', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-distribute-card-parameter-step', { outlets: { popup: null } }]);
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
