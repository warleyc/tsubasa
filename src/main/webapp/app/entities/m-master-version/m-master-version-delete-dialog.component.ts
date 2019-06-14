import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMasterVersion } from 'app/shared/model/m-master-version.model';
import { MMasterVersionService } from './m-master-version.service';

@Component({
  selector: 'jhi-m-master-version-delete-dialog',
  templateUrl: './m-master-version-delete-dialog.component.html'
})
export class MMasterVersionDeleteDialogComponent {
  mMasterVersion: IMMasterVersion;

  constructor(
    protected mMasterVersionService: MMasterVersionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMasterVersionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMasterVersionListModification',
        content: 'Deleted an mMasterVersion'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-master-version-delete-popup',
  template: ''
})
export class MMasterVersionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMasterVersion }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMasterVersionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMasterVersion = mMasterVersion;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-master-version', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-master-version', { outlets: { popup: null } }]);
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
