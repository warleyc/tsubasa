import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';
import { MUniformOriginalSetService } from './m-uniform-original-set.service';

@Component({
  selector: 'jhi-m-uniform-original-set-delete-dialog',
  templateUrl: './m-uniform-original-set-delete-dialog.component.html'
})
export class MUniformOriginalSetDeleteDialogComponent {
  mUniformOriginalSet: IMUniformOriginalSet;

  constructor(
    protected mUniformOriginalSetService: MUniformOriginalSetService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mUniformOriginalSetService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mUniformOriginalSetListModification',
        content: 'Deleted an mUniformOriginalSet'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-uniform-original-set-delete-popup',
  template: ''
})
export class MUniformOriginalSetDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUniformOriginalSet }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MUniformOriginalSetDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mUniformOriginalSet = mUniformOriginalSet;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-uniform-original-set', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-uniform-original-set', { outlets: { popup: null } }]);
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
