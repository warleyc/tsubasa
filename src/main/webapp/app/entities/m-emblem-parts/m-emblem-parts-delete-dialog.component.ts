import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEmblemParts } from 'app/shared/model/m-emblem-parts.model';
import { MEmblemPartsService } from './m-emblem-parts.service';

@Component({
  selector: 'jhi-m-emblem-parts-delete-dialog',
  templateUrl: './m-emblem-parts-delete-dialog.component.html'
})
export class MEmblemPartsDeleteDialogComponent {
  mEmblemParts: IMEmblemParts;

  constructor(
    protected mEmblemPartsService: MEmblemPartsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEmblemPartsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEmblemPartsListModification',
        content: 'Deleted an mEmblemParts'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-emblem-parts-delete-popup',
  template: ''
})
export class MEmblemPartsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEmblemParts }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEmblemPartsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mEmblemParts = mEmblemParts;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-emblem-parts', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-emblem-parts', { outlets: { popup: null } }]);
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
