import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLeagueEffect } from 'app/shared/model/m-league-effect.model';
import { MLeagueEffectService } from './m-league-effect.service';

@Component({
  selector: 'jhi-m-league-effect-delete-dialog',
  templateUrl: './m-league-effect-delete-dialog.component.html'
})
export class MLeagueEffectDeleteDialogComponent {
  mLeagueEffect: IMLeagueEffect;

  constructor(
    protected mLeagueEffectService: MLeagueEffectService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLeagueEffectService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLeagueEffectListModification',
        content: 'Deleted an mLeagueEffect'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-league-effect-delete-popup',
  template: ''
})
export class MLeagueEffectDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueEffect }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLeagueEffectDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLeagueEffect = mLeagueEffect;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-league-effect', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-league-effect', { outlets: { popup: null } }]);
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
