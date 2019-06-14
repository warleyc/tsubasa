import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';
import { MGuildEffectLevelService } from './m-guild-effect-level.service';

@Component({
  selector: 'jhi-m-guild-effect-level-delete-dialog',
  templateUrl: './m-guild-effect-level-delete-dialog.component.html'
})
export class MGuildEffectLevelDeleteDialogComponent {
  mGuildEffectLevel: IMGuildEffectLevel;

  constructor(
    protected mGuildEffectLevelService: MGuildEffectLevelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildEffectLevelService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildEffectLevelListModification',
        content: 'Deleted an mGuildEffectLevel'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-effect-level-delete-popup',
  template: ''
})
export class MGuildEffectLevelDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildEffectLevel }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildEffectLevelDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGuildEffectLevel = mGuildEffectLevel;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-effect-level', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-effect-level', { outlets: { popup: null } }]);
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
