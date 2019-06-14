import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMUniformBottom } from 'app/shared/model/m-uniform-bottom.model';
import { MUniformBottomService } from './m-uniform-bottom.service';

@Component({
  selector: 'jhi-m-uniform-bottom-delete-dialog',
  templateUrl: './m-uniform-bottom-delete-dialog.component.html'
})
export class MUniformBottomDeleteDialogComponent {
  mUniformBottom: IMUniformBottom;

  constructor(
    protected mUniformBottomService: MUniformBottomService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mUniformBottomService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mUniformBottomListModification',
        content: 'Deleted an mUniformBottom'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-uniform-bottom-delete-popup',
  template: ''
})
export class MUniformBottomDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUniformBottom }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MUniformBottomDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mUniformBottom = mUniformBottom;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-uniform-bottom', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-uniform-bottom', { outlets: { popup: null } }]);
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
