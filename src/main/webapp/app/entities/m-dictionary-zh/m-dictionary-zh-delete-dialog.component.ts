import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';
import { MDictionaryZhService } from './m-dictionary-zh.service';

@Component({
  selector: 'jhi-m-dictionary-zh-delete-dialog',
  templateUrl: './m-dictionary-zh-delete-dialog.component.html'
})
export class MDictionaryZhDeleteDialogComponent {
  mDictionaryZh: IMDictionaryZh;

  constructor(
    protected mDictionaryZhService: MDictionaryZhService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDictionaryZhService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDictionaryZhListModification',
        content: 'Deleted an mDictionaryZh'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dictionary-zh-delete-popup',
  template: ''
})
export class MDictionaryZhDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryZh }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDictionaryZhDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDictionaryZh = mDictionaryZh;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dictionary-zh', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dictionary-zh', { outlets: { popup: null } }]);
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
