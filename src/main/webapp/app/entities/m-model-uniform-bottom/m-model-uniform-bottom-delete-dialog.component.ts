import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';
import { MModelUniformBottomService } from './m-model-uniform-bottom.service';

@Component({
  selector: 'jhi-m-model-uniform-bottom-delete-dialog',
  templateUrl: './m-model-uniform-bottom-delete-dialog.component.html'
})
export class MModelUniformBottomDeleteDialogComponent {
  mModelUniformBottom: IMModelUniformBottom;

  constructor(
    protected mModelUniformBottomService: MModelUniformBottomService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mModelUniformBottomService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mModelUniformBottomListModification',
        content: 'Deleted an mModelUniformBottom'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-model-uniform-bottom-delete-popup',
  template: ''
})
export class MModelUniformBottomDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformBottom }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MModelUniformBottomDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mModelUniformBottom = mModelUniformBottom;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-model-uniform-bottom', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-model-uniform-bottom', { outlets: { popup: null } }]);
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
