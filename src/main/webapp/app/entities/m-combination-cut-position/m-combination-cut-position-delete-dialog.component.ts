import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCombinationCutPosition } from 'app/shared/model/m-combination-cut-position.model';
import { MCombinationCutPositionService } from './m-combination-cut-position.service';

@Component({
  selector: 'jhi-m-combination-cut-position-delete-dialog',
  templateUrl: './m-combination-cut-position-delete-dialog.component.html'
})
export class MCombinationCutPositionDeleteDialogComponent {
  mCombinationCutPosition: IMCombinationCutPosition;

  constructor(
    protected mCombinationCutPositionService: MCombinationCutPositionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCombinationCutPositionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCombinationCutPositionListModification',
        content: 'Deleted an mCombinationCutPosition'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-combination-cut-position-delete-popup',
  template: ''
})
export class MCombinationCutPositionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCombinationCutPosition }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCombinationCutPositionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mCombinationCutPosition = mCombinationCutPosition;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-combination-cut-position', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-combination-cut-position', { outlets: { popup: null } }]);
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
