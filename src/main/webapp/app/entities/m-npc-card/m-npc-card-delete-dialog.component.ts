import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMNpcCard } from 'app/shared/model/m-npc-card.model';
import { MNpcCardService } from './m-npc-card.service';

@Component({
  selector: 'jhi-m-npc-card-delete-dialog',
  templateUrl: './m-npc-card-delete-dialog.component.html'
})
export class MNpcCardDeleteDialogComponent {
  mNpcCard: IMNpcCard;

  constructor(protected mNpcCardService: MNpcCardService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mNpcCardService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mNpcCardListModification',
        content: 'Deleted an mNpcCard'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-npc-card-delete-popup',
  template: ''
})
export class MNpcCardDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNpcCard }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MNpcCardDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mNpcCard = mNpcCard;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-npc-card', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-npc-card', { outlets: { popup: null } }]);
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
