import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';
import { MMarathonEffectiveCardService } from './m-marathon-effective-card.service';

@Component({
  selector: 'jhi-m-marathon-effective-card-delete-dialog',
  templateUrl: './m-marathon-effective-card-delete-dialog.component.html'
})
export class MMarathonEffectiveCardDeleteDialogComponent {
  mMarathonEffectiveCard: IMMarathonEffectiveCard;

  constructor(
    protected mMarathonEffectiveCardService: MMarathonEffectiveCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonEffectiveCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonEffectiveCardListModification',
        content: 'Deleted an mMarathonEffectiveCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-effective-card-delete-popup',
  template: ''
})
export class MMarathonEffectiveCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonEffectiveCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonEffectiveCardDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonEffectiveCard = mMarathonEffectiveCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-effective-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-effective-card', { outlets: { popup: null } }]);
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
