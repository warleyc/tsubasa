import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';
import { MPvpRegulationService } from './m-pvp-regulation.service';

@Component({
  selector: 'jhi-m-pvp-regulation-delete-dialog',
  templateUrl: './m-pvp-regulation-delete-dialog.component.html'
})
export class MPvpRegulationDeleteDialogComponent {
  mPvpRegulation: IMPvpRegulation;

  constructor(
    protected mPvpRegulationService: MPvpRegulationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpRegulationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpRegulationListModification',
        content: 'Deleted an mPvpRegulation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-regulation-delete-popup',
  template: ''
})
export class MPvpRegulationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpRegulation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpRegulationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPvpRegulation = mPvpRegulation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-regulation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-regulation', { outlets: { popup: null } }]);
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
