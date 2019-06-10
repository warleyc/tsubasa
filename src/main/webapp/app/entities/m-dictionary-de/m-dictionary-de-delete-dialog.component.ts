import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryDe } from 'app/shared/model/m-dictionary-de.model';
import { MDictionaryDeService } from './m-dictionary-de.service';

@Component({
  selector: 'jhi-m-dictionary-de-delete-dialog',
  templateUrl: './m-dictionary-de-delete-dialog.component.html'
})
export class MDictionaryDeDeleteDialogComponent {
  mDictionaryDe: IMDictionaryDe;

  constructor(
    protected mDictionaryDeService: MDictionaryDeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryDeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryDeListModification',
        content: 'Deleted an mDictionaryDe'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-de-delete-popup',
  template: ''
})
export class MDictionaryDeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryDe }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryDeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryDe = mDictionaryDe;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-de', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-de', { outlets: { popup: null } }]);
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
