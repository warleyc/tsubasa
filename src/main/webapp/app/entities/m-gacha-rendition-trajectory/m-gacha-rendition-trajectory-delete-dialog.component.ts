import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';
import { MGachaRenditionTrajectoryService } from './m-gacha-rendition-trajectory.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-delete-dialog',
  templateUrl: './m-gacha-rendition-trajectory-delete-dialog.component.html'
})
export class MGachaRenditionTrajectoryDeleteDialogComponent {
  mGachaRenditionTrajectory: IMGachaRenditionTrajectory;

  constructor(
    protected mGachaRenditionTrajectoryService: MGachaRenditionTrajectoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionTrajectoryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionTrajectoryListModification',
        content: 'Deleted an mGachaRenditionTrajectory'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-delete-popup',
  template: ''
})
export class MGachaRenditionTrajectoryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectory }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionTrajectoryDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionTrajectory = mGachaRenditionTrajectory;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-trajectory', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-trajectory', { outlets: { popup: null } }]);
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
