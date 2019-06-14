import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';
import { MMarathonBoostScheduleService } from './m-marathon-boost-schedule.service';

@Component({
  selector: 'jhi-m-marathon-boost-schedule-delete-dialog',
  templateUrl: './m-marathon-boost-schedule-delete-dialog.component.html'
})
export class MMarathonBoostScheduleDeleteDialogComponent {
  mMarathonBoostSchedule: IMMarathonBoostSchedule;

  constructor(
    protected mMarathonBoostScheduleService: MMarathonBoostScheduleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonBoostScheduleService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonBoostScheduleListModification',
        content: 'Deleted an mMarathonBoostSchedule'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-boost-schedule-delete-popup',
  template: ''
})
export class MMarathonBoostScheduleDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonBoostSchedule }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonBoostScheduleDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonBoostSchedule = mMarathonBoostSchedule;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-boost-schedule', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-boost-schedule', { outlets: { popup: null } }]);
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
