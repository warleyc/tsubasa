import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMUniformUp } from 'app/shared/model/m-uniform-up.model';
import { MUniformUpService } from './m-uniform-up.service';

@Component({
  selector: 'jhi-m-uniform-up-delete-dialog',
  templateUrl: './m-uniform-up-delete-dialog.component.html'
})
export class MUniformUpDeleteDialogComponent {
  mUniformUp: IMUniformUp;

  constructor(
    protected mUniformUpService: MUniformUpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mUniformUpService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mUniformUpListModification',
        content: 'Deleted an mUniformUp'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-uniform-up-delete-popup',
  template: ''
})
export class MUniformUpDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUniformUp }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MUniformUpDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mUniformUp = mUniformUp;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-uniform-up', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-uniform-up', { outlets: { popup: null } }]);
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
