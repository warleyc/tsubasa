import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMNgWord } from 'app/shared/model/m-ng-word.model';
import { MNgWordService } from './m-ng-word.service';

@Component({
  selector: 'jhi-m-ng-word-delete-dialog',
  templateUrl: './m-ng-word-delete-dialog.component.html'
})
export class MNgWordDeleteDialogComponent {
  mNgWord: IMNgWord;

  constructor(protected mNgWordService: MNgWordService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mNgWordService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mNgWordListModification',
        content: 'Deleted an mNgWord'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-ng-word-delete-popup',
  template: ''
})
export class MNgWordDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNgWord }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MNgWordDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mNgWord = mNgWord;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-ng-word', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-ng-word', { outlets: { popup: null } }]);
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
