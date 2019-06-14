import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMNpcDeck } from 'app/shared/model/m-npc-deck.model';
import { MNpcDeckService } from './m-npc-deck.service';

@Component({
  selector: 'jhi-m-npc-deck-delete-dialog',
  templateUrl: './m-npc-deck-delete-dialog.component.html'
})
export class MNpcDeckDeleteDialogComponent {
  mNpcDeck: IMNpcDeck;

  constructor(protected mNpcDeckService: MNpcDeckService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mNpcDeckService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mNpcDeckListModification',
        content: 'Deleted an mNpcDeck'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-npc-deck-delete-popup',
  template: ''
})
export class MNpcDeckDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNpcDeck }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MNpcDeckDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mNpcDeck = mNpcDeck;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-npc-deck', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-npc-deck', { outlets: { popup: null } }]);
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
