import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCharacterBook } from 'app/shared/model/m-character-book.model';
import { MCharacterBookService } from './m-character-book.service';

@Component({
  selector: 'jhi-m-character-book-delete-dialog',
  templateUrl: './m-character-book-delete-dialog.component.html'
})
export class MCharacterBookDeleteDialogComponent {
  mCharacterBook: IMCharacterBook;

  constructor(
    protected mCharacterBookService: MCharacterBookService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCharacterBookService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCharacterBookListModification',
        content: 'Deleted an mCharacterBook'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-character-book-delete-popup',
  template: ''
})
export class MCharacterBookDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCharacterBook }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCharacterBookDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCharacterBook = mCharacterBook;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-character-book', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-character-book', { outlets: { popup: null } }]);
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
