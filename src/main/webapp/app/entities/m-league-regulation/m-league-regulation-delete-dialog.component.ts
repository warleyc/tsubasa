import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLeagueRegulation } from 'app/shared/model/m-league-regulation.model';
import { MLeagueRegulationService } from './m-league-regulation.service';

@Component({
  selector: 'jhi-m-league-regulation-delete-dialog',
  templateUrl: './m-league-regulation-delete-dialog.component.html'
})
export class MLeagueRegulationDeleteDialogComponent {
  mLeagueRegulation: IMLeagueRegulation;

  constructor(
    protected mLeagueRegulationService: MLeagueRegulationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLeagueRegulationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLeagueRegulationListModification',
        content: 'Deleted an mLeagueRegulation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-league-regulation-delete-popup',
  template: ''
})
export class MLeagueRegulationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueRegulation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLeagueRegulationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLeagueRegulation = mLeagueRegulation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-league-regulation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-league-regulation', { outlets: { popup: null } }]);
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
