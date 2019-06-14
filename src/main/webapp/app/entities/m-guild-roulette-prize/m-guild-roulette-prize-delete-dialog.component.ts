import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';
import { MGuildRoulettePrizeService } from './m-guild-roulette-prize.service';

@Component({
  selector: 'jhi-m-guild-roulette-prize-delete-dialog',
  templateUrl: './m-guild-roulette-prize-delete-dialog.component.html'
})
export class MGuildRoulettePrizeDeleteDialogComponent {
  mGuildRoulettePrize: IMGuildRoulettePrize;

  constructor(
    protected mGuildRoulettePrizeService: MGuildRoulettePrizeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildRoulettePrizeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildRoulettePrizeListModification',
        content: 'Deleted an mGuildRoulettePrize'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-roulette-prize-delete-popup',
  template: ''
})
export class MGuildRoulettePrizeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildRoulettePrize }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildRoulettePrizeDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGuildRoulettePrize = mGuildRoulettePrize;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-roulette-prize', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-roulette-prize', { outlets: { popup: null } }]);
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
