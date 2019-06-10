import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';
import { MDictionaryFrService } from './m-dictionary-fr.service';

@Component({
  selector: 'jhi-m-dictionary-fr-delete-dialog',
  templateUrl: './m-dictionary-fr-delete-dialog.component.html'
})
export class MDictionaryFrDeleteDialogComponent {
  mDictionaryFr: IMDictionaryFr;

  constructor(
    protected mDictionaryFrService: MDictionaryFrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryFrService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryFrListModification',
        content: 'Deleted an mDictionaryFr'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-fr-delete-popup',
  template: ''
})
export class MDictionaryFrDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryFr }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryFrDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryFr = mDictionaryFr;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-fr', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-fr', { outlets: { popup: null } }]);
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
