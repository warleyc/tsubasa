import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';
import { MTeamEffectBaseService } from './m-team-effect-base.service';

@Component({
  selector: 'jhi-m-team-effect-base-delete-dialog',
  templateUrl: './m-team-effect-base-delete-dialog.component.html'
})
export class MTeamEffectBaseDeleteDialogComponent {
  mTeamEffectBase: IMTeamEffectBase;

  constructor(
    protected mTeamEffectBaseService: MTeamEffectBaseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTeamEffectBaseService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTeamEffectBaseListModification',
        content: 'Deleted an mTeamEffectBase'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-team-effect-base-delete-popup',
  template: ''
})
export class MTeamEffectBaseDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeamEffectBase }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTeamEffectBaseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTeamEffectBase = mTeamEffectBase;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-team-effect-base', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-team-effect-base', { outlets: { popup: null } }]);
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
