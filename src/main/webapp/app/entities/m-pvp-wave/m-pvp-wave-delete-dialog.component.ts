import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpWave } from 'app/shared/model/m-pvp-wave.model';
import { MPvpWaveService } from './m-pvp-wave.service';

@Component({
  selector: 'jhi-m-pvp-wave-delete-dialog',
  templateUrl: './m-pvp-wave-delete-dialog.component.html'
})
export class MPvpWaveDeleteDialogComponent {
  mPvpWave: IMPvpWave;

  constructor(protected mPvpWaveService: MPvpWaveService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpWaveService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpWaveListModification',
        content: 'Deleted an mPvpWave'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-wave-delete-popup',
  template: ''
})
export class MPvpWaveDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpWave }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpWaveDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPvpWave = mPvpWave;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-wave', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-wave', { outlets: { popup: null } }]);
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
