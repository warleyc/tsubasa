import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetTeamGroup } from 'app/shared/model/m-target-team-group.model';
import { MTargetTeamGroupService } from './m-target-team-group.service';

@Component({
  selector: 'jhi-m-target-team-group-delete-dialog',
  templateUrl: './m-target-team-group-delete-dialog.component.html'
})
export class MTargetTeamGroupDeleteDialogComponent {
  mTargetTeamGroup: IMTargetTeamGroup;

  constructor(
    protected mTargetTeamGroupService: MTargetTeamGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetTeamGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetTeamGroupListModification',
        content: 'Deleted an mTargetTeamGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-team-group-delete-popup',
  template: ''
})
export class MTargetTeamGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetTeamGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetTeamGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTargetTeamGroup = mTargetTeamGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-team-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-team-group', { outlets: { popup: null } }]);
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
