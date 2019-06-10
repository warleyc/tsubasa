import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';
import { MDeckRarityConditionDescriptionService } from './m-deck-rarity-condition-description.service';

@Component({
  selector: 'jhi-m-deck-rarity-condition-description-delete-dialog',
  templateUrl: './m-deck-rarity-condition-description-delete-dialog.component.html'
})
export class MDeckRarityConditionDescriptionDeleteDialogComponent {
  mDeckRarityConditionDescription: IMDeckRarityConditionDescription;

  constructor(
    protected mDeckRarityConditionDescriptionService: MDeckRarityConditionDescriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDeckRarityConditionDescriptionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDeckRarityConditionDescriptionListModification',
        content: 'Deleted an mDeckRarityConditionDescription'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-deck-rarity-condition-description-delete-popup',
  template: ''
})
export class MDeckRarityConditionDescriptionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDeckRarityConditionDescription }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDeckRarityConditionDescriptionDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mDeckRarityConditionDescription = mDeckRarityConditionDescription;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-deck-rarity-condition-description', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-deck-rarity-condition-description', { outlets: { popup: null } }]);
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
