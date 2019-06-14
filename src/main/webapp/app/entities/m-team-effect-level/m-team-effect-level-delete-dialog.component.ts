import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';
import { MTeamEffectLevelService } from './m-team-effect-level.service';

@Component({
  selector: 'jhi-m-team-effect-level-delete-dialog',
  templateUrl: './m-team-effect-level-delete-dialog.component.html'
})
export class MTeamEffectLevelDeleteDialogComponent {
  mTeamEffectLevel: IMTeamEffectLevel;

  constructor(
    protected mTeamEffectLevelService: MTeamEffectLevelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTeamEffectLevelService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTeamEffectLevelListModification',
        content: 'Deleted an mTeamEffectLevel'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-team-effect-level-delete-popup',
  template: ''
})
export class MTeamEffectLevelDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeamEffectLevel }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTeamEffectLevelDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTeamEffectLevel = mTeamEffectLevel;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-team-effect-level', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-team-effect-level', { outlets: { popup: null } }]);
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
