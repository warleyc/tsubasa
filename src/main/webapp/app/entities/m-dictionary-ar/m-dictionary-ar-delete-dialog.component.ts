import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryAr } from 'app/shared/model/m-dictionary-ar.model';
import { MDictionaryArService } from './m-dictionary-ar.service';

@Component({
  selector: 'jhi-m-dictionary-ar-delete-dialog',
  templateUrl: './m-dictionary-ar-delete-dialog.component.html'
})
export class MDictionaryArDeleteDialogComponent {
  mDictionaryAr: IMDictionaryAr;

  constructor(
    protected mDictionaryArService: MDictionaryArService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryArService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryArListModification',
        content: 'Deleted an mDictionaryAr'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-ar-delete-popup',
  template: ''
})
export class MDictionaryArDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryAr }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryArDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryAr = mDictionaryAr;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-ar', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-ar', { outlets: { popup: null } }]);
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
