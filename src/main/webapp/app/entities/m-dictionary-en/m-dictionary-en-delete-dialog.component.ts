import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryEn } from 'app/shared/model/m-dictionary-en.model';
import { MDictionaryEnService } from './m-dictionary-en.service';

@Component({
  selector: 'jhi-m-dictionary-en-delete-dialog',
  templateUrl: './m-dictionary-en-delete-dialog.component.html'
})
export class MDictionaryEnDeleteDialogComponent {
  mDictionaryEn: IMDictionaryEn;

  constructor(
    protected mDictionaryEnService: MDictionaryEnService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryEnService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryEnListModification',
        content: 'Deleted an mDictionaryEn'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-en-delete-popup',
  template: ''
})
export class MDictionaryEnDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryEn }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryEnDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryEn = mDictionaryEn;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-en', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-en', { outlets: { popup: null } }]);
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
