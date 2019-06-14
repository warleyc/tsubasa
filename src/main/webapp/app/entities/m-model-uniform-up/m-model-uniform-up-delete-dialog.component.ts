import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';
import { MModelUniformUpService } from './m-model-uniform-up.service';

@Component({
  selector: 'jhi-m-model-uniform-up-delete-dialog',
  templateUrl: './m-model-uniform-up-delete-dialog.component.html'
})
export class MModelUniformUpDeleteDialogComponent {
  mModelUniformUp: IMModelUniformUp;

  constructor(
    protected mModelUniformUpService: MModelUniformUpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mModelUniformUpService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mModelUniformUpListModification',
        content: 'Deleted an mModelUniformUp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-model-uniform-up-delete-popup',
  template: ''
})
export class MModelUniformUpDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformUp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MModelUniformUpDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mModelUniformUp = mModelUniformUp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-model-uniform-up', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-model-uniform-up', { outlets: { popup: null } }]);
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
