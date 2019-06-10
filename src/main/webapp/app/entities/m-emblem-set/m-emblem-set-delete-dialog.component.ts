import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEmblemSet } from 'app/shared/model/m-emblem-set.model';
import { MEmblemSetService } from './m-emblem-set.service';

@Component({
  selector: 'jhi-m-emblem-set-delete-dialog',
  templateUrl: './m-emblem-set-delete-dialog.component.html'
})
export class MEmblemSetDeleteDialogComponent {
  mEmblemSet: IMEmblemSet;

  constructor(
    protected mEmblemSetService: MEmblemSetService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEmblemSetService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEmblemSetListModification',
        content: 'Deleted an mEmblemSet'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-emblem-set-delete-popup',
  template: ''
})
export class MEmblemSetDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEmblemSet }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEmblemSetDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mEmblemSet = mEmblemSet;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-emblem-set', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-emblem-set', { outlets: { popup: null } }]);
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
