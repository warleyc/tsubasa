import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLeague } from 'app/shared/model/m-league.model';
import { MLeagueService } from './m-league.service';

@Component({
  selector: 'jhi-m-league-delete-dialog',
  templateUrl: './m-league-delete-dialog.component.html'
})
export class MLeagueDeleteDialogComponent {
  mLeague: IMLeague;

  constructor(protected mLeagueService: MLeagueService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLeagueService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLeagueListModification',
        content: 'Deleted an mLeague'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-league-delete-popup',
  template: ''
})
export class MLeagueDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeague }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLeagueDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLeague = mLeague;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-league', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-league', { outlets: { popup: null } }]);
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
