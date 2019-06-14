import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMission } from 'app/shared/model/m-mission.model';
import { MMissionService } from './m-mission.service';

@Component({
  selector: 'jhi-m-mission-delete-dialog',
  templateUrl: './m-mission-delete-dialog.component.html'
})
export class MMissionDeleteDialogComponent {
  mMission: IMMission;

  constructor(protected mMissionService: MMissionService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMissionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMissionListModification',
        content: 'Deleted an mMission'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-mission-delete-popup',
  template: ''
})
export class MMissionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMission }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMissionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMission = mMission;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-mission', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-mission', { outlets: { popup: null } }]);
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
