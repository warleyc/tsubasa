import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildEffect } from 'app/shared/model/m-guild-effect.model';
import { MGuildEffectService } from './m-guild-effect.service';

@Component({
  selector: 'jhi-m-guild-effect-delete-dialog',
  templateUrl: './m-guild-effect-delete-dialog.component.html'
})
export class MGuildEffectDeleteDialogComponent {
  mGuildEffect: IMGuildEffect;

  constructor(
    protected mGuildEffectService: MGuildEffectService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildEffectService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildEffectListModification',
        content: 'Deleted an mGuildEffect'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-effect-delete-popup',
  template: ''
})
export class MGuildEffectDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildEffect }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildEffectDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGuildEffect = mGuildEffect;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-effect', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-effect', { outlets: { popup: null } }]);
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
