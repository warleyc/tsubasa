import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMActionRarity } from 'app/shared/model/m-action-rarity.model';
import { MActionRarityService } from './m-action-rarity.service';

@Component({
  selector: 'jhi-m-action-rarity-delete-dialog',
  templateUrl: './m-action-rarity-delete-dialog.component.html'
})
export class MActionRarityDeleteDialogComponent {
  mActionRarity: IMActionRarity;

  constructor(
    protected mActionRarityService: MActionRarityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mActionRarityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mActionRarityListModification',
        content: 'Deleted an mActionRarity'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-action-rarity-delete-popup',
  template: ''
})
export class MActionRarityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionRarity }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MActionRarityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mActionRarity = mActionRarity;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-action-rarity', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-action-rarity', { outlets: { popup: null } }]);
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
