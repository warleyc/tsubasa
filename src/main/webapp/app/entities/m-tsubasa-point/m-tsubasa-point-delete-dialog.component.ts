import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';
import { MTsubasaPointService } from './m-tsubasa-point.service';

@Component({
  selector: 'jhi-m-tsubasa-point-delete-dialog',
  templateUrl: './m-tsubasa-point-delete-dialog.component.html'
})
export class MTsubasaPointDeleteDialogComponent {
  mTsubasaPoint: IMTsubasaPoint;

  constructor(
    protected mTsubasaPointService: MTsubasaPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTsubasaPointService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTsubasaPointListModification',
        content: 'Deleted an mTsubasaPoint'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-tsubasa-point-delete-popup',
  template: ''
})
export class MTsubasaPointDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTsubasaPoint }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTsubasaPointDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTsubasaPoint = mTsubasaPoint;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-tsubasa-point', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-tsubasa-point', { outlets: { popup: null } }]);
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
