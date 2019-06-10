import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';
import { MDistributeCardParameterService } from './m-distribute-card-parameter.service';

@Component({
  selector: 'jhi-m-distribute-card-parameter-delete-dialog',
  templateUrl: './m-distribute-card-parameter-delete-dialog.component.html'
})
export class MDistributeCardParameterDeleteDialogComponent {
  mDistributeCardParameter: IMDistributeCardParameter;

  constructor(
    protected mDistributeCardParameterService: MDistributeCardParameterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDistributeCardParameterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDistributeCardParameterListModification',
        content: 'Deleted an mDistributeCardParameter'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-distribute-card-parameter-delete-popup',
  template: ''
})
export class MDistributeCardParameterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDistributeCardParameter }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDistributeCardParameterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mDistributeCardParameter = mDistributeCardParameter;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-distribute-card-parameter', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-distribute-card-parameter', { outlets: { popup: null } }]);
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
