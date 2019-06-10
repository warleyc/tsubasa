import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';
import { MDictionaryPtService } from './m-dictionary-pt.service';

@Component({
  selector: 'jhi-m-dictionary-pt-delete-dialog',
  templateUrl: './m-dictionary-pt-delete-dialog.component.html'
})
export class MDictionaryPtDeleteDialogComponent {
  mDictionaryPt: IMDictionaryPt;

  constructor(
    protected mDictionaryPtService: MDictionaryPtService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryPtService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryPtListModification',
        content: 'Deleted an mDictionaryPt'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-pt-delete-popup',
  template: ''
})
export class MDictionaryPtDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryPt }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryPtDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryPt = mDictionaryPt;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-pt', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-pt', { outlets: { popup: null } }]);
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
