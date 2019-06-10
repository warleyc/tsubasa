import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMFullPowerPoint } from 'app/shared/model/m-full-power-point.model';
import { MFullPowerPointService } from './m-full-power-point.service';

@Component({
  selector: 'jhi-m-full-power-point-delete-dialog',
  templateUrl: './m-full-power-point-delete-dialog.component.html'
})
export class MFullPowerPointDeleteDialogComponent {
  mFullPowerPoint: IMFullPowerPoint;

  constructor(
    protected mFullPowerPointService: MFullPowerPointService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mFullPowerPointService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mFullPowerPointListModification',
        content: 'Deleted an mFullPowerPoint'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-full-power-point-delete-popup',
  template: ''
})
export class MFullPowerPointDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mFullPowerPoint }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MFullPowerPointDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mFullPowerPoint = mFullPowerPoint;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-full-power-point', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-full-power-point', { outlets: { popup: null } }]);
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
