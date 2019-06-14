import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';
import { MGuildNegativeWordService } from './m-guild-negative-word.service';

@Component({
  selector: 'jhi-m-guild-negative-word-delete-dialog',
  templateUrl: './m-guild-negative-word-delete-dialog.component.html'
})
export class MGuildNegativeWordDeleteDialogComponent {
  mGuildNegativeWord: IMGuildNegativeWord;

  constructor(
    protected mGuildNegativeWordService: MGuildNegativeWordService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuildNegativeWordService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuildNegativeWordListModification',
        content: 'Deleted an mGuildNegativeWord'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guild-negative-word-delete-popup',
  template: ''
})
export class MGuildNegativeWordDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildNegativeWord }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuildNegativeWordDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mGuildNegativeWord = mGuildNegativeWord;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guild-negative-word', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guild-negative-word', { outlets: { popup: null } }]);
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
