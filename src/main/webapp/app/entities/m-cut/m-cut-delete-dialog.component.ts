import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCut } from 'app/shared/model/m-cut.model';
import { MCutService } from './m-cut.service';

@Component({
  selector: 'jhi-m-cut-delete-dialog',
  templateUrl: './m-cut-delete-dialog.component.html'
})
export class MCutDeleteDialogComponent {
  mCut: IMCut;

  constructor(protected mCutService: MCutService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCutService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCutListModification',
        content: 'Deleted an mCut'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-cut-delete-popup',
  template: ''
})
export class MCutDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCut }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCutDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCut = mCut;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-cut', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-cut', { outlets: { popup: null } }]);
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
