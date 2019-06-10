import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMContentableCard } from 'app/shared/model/m-contentable-card.model';
import { MContentableCardService } from './m-contentable-card.service';

@Component({
  selector: 'jhi-m-contentable-card-delete-dialog',
  templateUrl: './m-contentable-card-delete-dialog.component.html'
})
export class MContentableCardDeleteDialogComponent {
  mContentableCard: IMContentableCard;

  constructor(
    protected mContentableCardService: MContentableCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mContentableCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mContentableCardListModification',
        content: 'Deleted an mContentableCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-contentable-card-delete-popup',
  template: ''
})
export class MContentableCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mContentableCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MContentableCardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mContentableCard = mContentableCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-contentable-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-contentable-card', { outlets: { popup: null } }]);
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
