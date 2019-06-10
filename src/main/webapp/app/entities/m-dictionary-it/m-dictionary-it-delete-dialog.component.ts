import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryIt } from 'app/shared/model/m-dictionary-it.model';
import { MDictionaryItService } from './m-dictionary-it.service';

@Component({
  selector: 'jhi-m-dictionary-it-delete-dialog',
  templateUrl: './m-dictionary-it-delete-dialog.component.html'
})
export class MDictionaryItDeleteDialogComponent {
  mDictionaryIt: IMDictionaryIt;

  constructor(
    protected mDictionaryItService: MDictionaryItService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryItService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryItListModification',
        content: 'Deleted an mDictionaryIt'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-it-delete-popup',
  template: ''
})
export class MDictionaryItDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryIt }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryItDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryIt = mDictionaryIt;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-it', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-it', { outlets: { popup: null } }]);
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
