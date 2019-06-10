import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';
import { MDictionaryJaService } from './m-dictionary-ja.service';

@Component({
  selector: 'jhi-m-dictionary-ja-delete-dialog',
  templateUrl: './m-dictionary-ja-delete-dialog.component.html'
})
export class MDictionaryJaDeleteDialogComponent {
  mDictionaryJa: IMDictionaryJa;

  constructor(
    protected mDictionaryJaService: MDictionaryJaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryJaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryJaListModification',
        content: 'Deleted an mDictionaryJa'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-ja-delete-popup',
  template: ''
})
export class MDictionaryJaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryJa }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryJaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryJa = mDictionaryJa;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-ja', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-ja', { outlets: { popup: null } }]);
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
