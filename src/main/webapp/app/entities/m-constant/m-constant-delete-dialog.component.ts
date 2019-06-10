import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMConstant } from 'app/shared/model/m-constant.model';
import { MConstantService } from './m-constant.service';

@Component({
  selector: 'jhi-m-constant-delete-dialog',
  templateUrl: './m-constant-delete-dialog.component.html'
})
export class MConstantDeleteDialogComponent {
  mConstant: IMConstant;

  constructor(protected mConstantService: MConstantService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mConstantService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mConstantListModification',
        content: 'Deleted an mConstant'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-constant-delete-popup',
  template: ''
})
export class MConstantDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mConstant }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MConstantDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mConstant = mConstant;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-constant', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-constant', { outlets: { popup: null } }]);
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
