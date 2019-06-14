import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';
import { MModelUniformUpResourceService } from './m-model-uniform-up-resource.service';

@Component({
  selector: 'jhi-m-model-uniform-up-resource-delete-dialog',
  templateUrl: './m-model-uniform-up-resource-delete-dialog.component.html'
})
export class MModelUniformUpResourceDeleteDialogComponent {
  mModelUniformUpResource: IMModelUniformUpResource;

  constructor(
    protected mModelUniformUpResourceService: MModelUniformUpResourceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mModelUniformUpResourceService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mModelUniformUpResourceListModification',
        content: 'Deleted an mModelUniformUpResource'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-model-uniform-up-resource-delete-popup',
  template: ''
})
export class MModelUniformUpResourceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformUpResource }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MModelUniformUpResourceDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mModelUniformUpResource = mModelUniformUpResource;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-model-uniform-up-resource', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-model-uniform-up-resource', { outlets: { popup: null } }]);
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
