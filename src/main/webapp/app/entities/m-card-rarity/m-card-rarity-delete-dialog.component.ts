import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCardRarity } from 'app/shared/model/m-card-rarity.model';
import { MCardRarityService } from './m-card-rarity.service';

@Component({
  selector: 'jhi-m-card-rarity-delete-dialog',
  templateUrl: './m-card-rarity-delete-dialog.component.html'
})
export class MCardRarityDeleteDialogComponent {
  mCardRarity: IMCardRarity;

  constructor(
    protected mCardRarityService: MCardRarityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCardRarityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCardRarityListModification',
        content: 'Deleted an mCardRarity'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-card-rarity-delete-popup',
  template: ''
})
export class MCardRarityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardRarity }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCardRarityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCardRarity = mCardRarity;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-card-rarity', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-card-rarity', { outlets: { popup: null } }]);
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
