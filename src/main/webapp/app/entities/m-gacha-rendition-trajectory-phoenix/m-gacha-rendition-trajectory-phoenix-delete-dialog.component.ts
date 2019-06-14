import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';
import { MGachaRenditionTrajectoryPhoenixService } from './m-gacha-rendition-trajectory-phoenix.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-phoenix-delete-dialog',
  templateUrl: './m-gacha-rendition-trajectory-phoenix-delete-dialog.component.html'
})
export class MGachaRenditionTrajectoryPhoenixDeleteDialogComponent {
  mGachaRenditionTrajectoryPhoenix: IMGachaRenditionTrajectoryPhoenix;

  constructor(
    protected mGachaRenditionTrajectoryPhoenixService: MGachaRenditionTrajectoryPhoenixService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionTrajectoryPhoenixService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionTrajectoryPhoenixListModification',
        content: 'Deleted an mGachaRenditionTrajectoryPhoenix'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-phoenix-delete-popup',
  template: ''
})
export class MGachaRenditionTrajectoryPhoenixDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectoryPhoenix }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionTrajectoryPhoenixDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenix;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-trajectory-phoenix', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-trajectory-phoenix', { outlets: { popup: null } }]);
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
