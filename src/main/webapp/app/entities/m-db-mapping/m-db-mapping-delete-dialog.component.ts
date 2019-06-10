import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDbMapping } from 'app/shared/model/m-db-mapping.model';
import { MDbMappingService } from './m-db-mapping.service';

@Component({
  selector: 'jhi-m-db-mapping-delete-dialog',
  templateUrl: './m-db-mapping-delete-dialog.component.html'
})
export class MDbMappingDeleteDialogComponent {
  mDbMapping: IMDbMapping;

  constructor(
    protected mDbMappingService: MDbMappingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDbMappingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDbMappingListModification',
        content: 'Deleted an mDbMapping'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-db-mapping-delete-popup',
  template: ''
})
export class MDbMappingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDbMapping }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDbMappingDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDbMapping = mDbMapping;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-db-mapping', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-db-mapping', { outlets: { popup: null } }]);
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
