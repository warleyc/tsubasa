import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';
import { MDummyOpponentService } from './m-dummy-opponent.service';

@Component({
  selector: 'jhi-m-dummy-opponent-delete-dialog',
  templateUrl: './m-dummy-opponent-delete-dialog.component.html'
})
export class MDummyOpponentDeleteDialogComponent {
  mDummyOpponent: IMDummyOpponent;

  constructor(
    protected mDummyOpponentService: MDummyOpponentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDummyOpponentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDummyOpponentListModification',
        content: 'Deleted an mDummyOpponent'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dummy-opponent-delete-popup',
  template: ''
})
export class MDummyOpponentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDummyOpponent }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDummyOpponentDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDummyOpponent = mDummyOpponent;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dummy-opponent', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dummy-opponent', { outlets: { popup: null } }]);
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
