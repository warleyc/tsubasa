import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDeckCondition } from 'app/shared/model/m-deck-condition.model';
import { MDeckConditionService } from './m-deck-condition.service';

@Component({
  selector: 'jhi-m-deck-condition-delete-dialog',
  templateUrl: './m-deck-condition-delete-dialog.component.html'
})
export class MDeckConditionDeleteDialogComponent {
  mDeckCondition: IMDeckCondition;

  constructor(
    protected mDeckConditionService: MDeckConditionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDeckConditionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDeckConditionListModification',
        content: 'Deleted an mDeckCondition'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-deck-condition-delete-popup',
  template: ''
})
export class MDeckConditionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDeckCondition }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDeckConditionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mDeckCondition = mDeckCondition;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-deck-condition', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-deck-condition', { outlets: { popup: null } }]);
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
