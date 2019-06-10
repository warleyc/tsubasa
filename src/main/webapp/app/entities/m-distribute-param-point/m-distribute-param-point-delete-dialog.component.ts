import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';
import { MDistributeParamPointService } from './m-distribute-param-point.service';

@Component({
  selector: 'jhi-m-distribute-param-point-delete-dialog',
  templateUrl: './m-distribute-param-point-delete-dialog.component.html'
})
export class MDistributeParamPointDeleteDialogComponent {
  mDistributeParamPoint: IMDistributeParamPoint;

  constructor(
    protected mDistributeParamPointService: MDistributeParamPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDistributeParamPointService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDistributeParamPointListModification',
        content: 'Deleted an mDistributeParamPoint'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-distribute-param-point-delete-popup',
  template: ''
})
export class MDistributeParamPointDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDistributeParamPoint }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDistributeParamPointDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mDistributeParamPoint = mDistributeParamPoint;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-distribute-param-point', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-distribute-param-point', { outlets: { popup: null } }]);
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
