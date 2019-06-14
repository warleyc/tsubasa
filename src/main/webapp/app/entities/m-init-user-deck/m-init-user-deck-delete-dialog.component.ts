import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMInitUserDeck } from 'app/shared/model/m-init-user-deck.model';
import { MInitUserDeckService } from './m-init-user-deck.service';

@Component({
  selector: 'jhi-m-init-user-deck-delete-dialog',
  templateUrl: './m-init-user-deck-delete-dialog.component.html'
})
export class MInitUserDeckDeleteDialogComponent {
  mInitUserDeck: IMInitUserDeck;

  constructor(
    protected mInitUserDeckService: MInitUserDeckService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mInitUserDeckService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mInitUserDeckListModification',
        content: 'Deleted an mInitUserDeck'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-init-user-deck-delete-popup',
  template: ''
})
export class MInitUserDeckDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mInitUserDeck }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MInitUserDeckDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mInitUserDeck = mInitUserDeck;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-init-user-deck', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-init-user-deck', { outlets: { popup: null } }]);
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
