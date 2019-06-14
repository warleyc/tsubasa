import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTeam } from 'app/shared/model/m-team.model';
import { MTeamService } from './m-team.service';

@Component({
  selector: 'jhi-m-team-delete-dialog',
  templateUrl: './m-team-delete-dialog.component.html'
})
export class MTeamDeleteDialogComponent {
  mTeam: IMTeam;

  constructor(protected mTeamService: MTeamService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTeamService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTeamListModification',
        content: 'Deleted an mTeam'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-team-delete-popup',
  template: ''
})
export class MTeamDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeam }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTeamDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTeam = mTeam;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-team', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-team', { outlets: { popup: null } }]);
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
