import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';
import { MDummyEmblemService } from './m-dummy-emblem.service';

@Component({
  selector: 'jhi-m-dummy-emblem-delete-dialog',
  templateUrl: './m-dummy-emblem-delete-dialog.component.html'
})
export class MDummyEmblemDeleteDialogComponent {
  mDummyEmblem: IMDummyEmblem;

  constructor(
    protected mDummyEmblemService: MDummyEmblemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDummyEmblemService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDummyEmblemListModification',
        content: 'Deleted an mDummyEmblem'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-dummy-emblem-delete-popup',
  template: ''
})
export class MDummyEmblemDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDummyEmblem }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDummyEmblemDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDummyEmblem = mDummyEmblem;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-dummy-emblem', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-dummy-emblem', { outlets: { popup: null } }]);
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
