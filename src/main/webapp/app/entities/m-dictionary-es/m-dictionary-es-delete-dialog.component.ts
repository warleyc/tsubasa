import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryEs } from 'app/shared/model/m-dictionary-es.model';
import { MDictionaryEsService } from './m-dictionary-es.service';

@Component({
  selector: 'jhi-m-dictionary-es-delete-dialog',
  templateUrl: './m-dictionary-es-delete-dialog.component.html'
})
export class MDictionaryEsDeleteDialogComponent {
  mDictionaryEs: IMDictionaryEs;

  constructor(
    protected mDictionaryEsService: MDictionaryEsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryEsService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryEsListModification',
        content: 'Deleted an mDictionaryEs'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-es-delete-popup',
  template: ''
})
export class MDictionaryEsDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryEs }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryEsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryEs = mDictionaryEs;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-es', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-es', { outlets: { popup: null } }]);
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
