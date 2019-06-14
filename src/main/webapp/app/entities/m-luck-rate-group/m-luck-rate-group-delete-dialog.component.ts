import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';
import { MLuckRateGroupService } from './m-luck-rate-group.service';

@Component({
  selector: 'jhi-m-luck-rate-group-delete-dialog',
  templateUrl: './m-luck-rate-group-delete-dialog.component.html'
})
export class MLuckRateGroupDeleteDialogComponent {
  mLuckRateGroup: IMLuckRateGroup;

  constructor(
    protected mLuckRateGroupService: MLuckRateGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLuckRateGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLuckRateGroupListModification',
        content: 'Deleted an mLuckRateGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-luck-rate-group-delete-popup',
  template: ''
})
export class MLuckRateGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckRateGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLuckRateGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mLuckRateGroup = mLuckRateGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-luck-rate-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-luck-rate-group', { outlets: { popup: null } }]);
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
