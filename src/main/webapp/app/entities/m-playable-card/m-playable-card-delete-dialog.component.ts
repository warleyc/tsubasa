import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPlayableCard } from 'app/shared/model/m-playable-card.model';
import { MPlayableCardService } from './m-playable-card.service';

@Component({
  selector: 'jhi-m-playable-card-delete-dialog',
  templateUrl: './m-playable-card-delete-dialog.component.html'
})
export class MPlayableCardDeleteDialogComponent {
  mPlayableCard: IMPlayableCard;

  constructor(
    protected mPlayableCardService: MPlayableCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPlayableCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPlayableCardListModification',
        content: 'Deleted an mPlayableCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-playable-card-delete-popup',
  template: ''
})
export class MPlayableCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPlayableCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPlayableCardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPlayableCard = mPlayableCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-playable-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-playable-card', { outlets: { popup: null } }]);
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
