import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';
import { MGoalEffectiveCardService } from './m-goal-effective-card.service';

@Component({
  selector: 'jhi-m-goal-effective-card-delete-dialog',
  templateUrl: './m-goal-effective-card-delete-dialog.component.html'
})
export class MGoalEffectiveCardDeleteDialogComponent {
  mGoalEffectiveCard: IMGoalEffectiveCard;

  constructor(
    protected mGoalEffectiveCardService: MGoalEffectiveCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGoalEffectiveCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGoalEffectiveCardListModification',
        content: 'Deleted an mGoalEffectiveCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-goal-effective-card-delete-popup',
  template: ''
})
export class MGoalEffectiveCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGoalEffectiveCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGoalEffectiveCardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGoalEffectiveCard = mGoalEffectiveCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-goal-effective-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-goal-effective-card', { outlets: { popup: null } }]);
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
