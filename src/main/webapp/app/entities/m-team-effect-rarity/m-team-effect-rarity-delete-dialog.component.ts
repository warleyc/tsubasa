import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTeamEffectRarity } from 'app/shared/model/m-team-effect-rarity.model';
import { MTeamEffectRarityService } from './m-team-effect-rarity.service';

@Component({
  selector: 'jhi-m-team-effect-rarity-delete-dialog',
  templateUrl: './m-team-effect-rarity-delete-dialog.component.html'
})
export class MTeamEffectRarityDeleteDialogComponent {
  mTeamEffectRarity: IMTeamEffectRarity;

  constructor(
    protected mTeamEffectRarityService: MTeamEffectRarityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTeamEffectRarityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTeamEffectRarityListModification',
        content: 'Deleted an mTeamEffectRarity'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-team-effect-rarity-delete-popup',
  template: ''
})
export class MTeamEffectRarityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTeamEffectRarity }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTeamEffectRarityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTeamEffectRarity = mTeamEffectRarity;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-team-effect-rarity', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-team-effect-rarity', { outlets: { popup: null } }]);
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
