import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildMission } from 'app/shared/model/m-guild-mission.model';
import { MGuildMissionService } from './m-guild-mission.service';

@Component({
  selector: 'jhi-m-guild-mission-delete-dialog',
  templateUrl: './m-guild-mission-delete-dialog.component.html'
})
export class MGuildMissionDeleteDialogComponent {
  mGuildMission: IMGuildMission;

  constructor(
    protected mGuildMissionService: MGuildMissionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildMissionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildMissionListModification',
        content: 'Deleted an mGuildMission'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-mission-delete-popup',
  template: ''
})
export class MGuildMissionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildMission }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildMissionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGuildMission = mGuildMission;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-mission', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-mission', { outlets: { popup: null } }]);
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
