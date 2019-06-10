import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';
import { MCutShootOrbitService } from './m-cut-shoot-orbit.service';

@Component({
  selector: 'jhi-m-cut-shoot-orbit-delete-dialog',
  templateUrl: './m-cut-shoot-orbit-delete-dialog.component.html'
})
export class MCutShootOrbitDeleteDialogComponent {
  mCutShootOrbit: IMCutShootOrbit;

  constructor(
    protected mCutShootOrbitService: MCutShootOrbitService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCutShootOrbitService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCutShootOrbitListModification',
        content: 'Deleted an mCutShootOrbit'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-cut-shoot-orbit-delete-popup',
  template: ''
})
export class MCutShootOrbitDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCutShootOrbit }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCutShootOrbitDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCutShootOrbit = mCutShootOrbit;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-cut-shoot-orbit', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-cut-shoot-orbit', { outlets: { popup: null } }]);
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
