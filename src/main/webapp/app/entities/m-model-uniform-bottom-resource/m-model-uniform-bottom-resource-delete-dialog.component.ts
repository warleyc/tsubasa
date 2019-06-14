import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';
import { MModelUniformBottomResourceService } from './m-model-uniform-bottom-resource.service';

@Component({
  selector: 'jhi-m-model-uniform-bottom-resource-delete-dialog',
  templateUrl: './m-model-uniform-bottom-resource-delete-dialog.component.html'
})
export class MModelUniformBottomResourceDeleteDialogComponent {
  mModelUniformBottomResource: IMModelUniformBottomResource;

  constructor(
    protected mModelUniformBottomResourceService: MModelUniformBottomResourceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mModelUniformBottomResourceService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mModelUniformBottomResourceListModification',
        content: 'Deleted an mModelUniformBottomResource'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-model-uniform-bottom-resource-delete-popup',
  template: ''
})
export class MModelUniformBottomResourceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformBottomResource }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MModelUniformBottomResourceDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mModelUniformBottomResource = mModelUniformBottomResource;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-model-uniform-bottom-resource', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-model-uniform-bottom-resource', { outlets: { popup: null } }]);
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
