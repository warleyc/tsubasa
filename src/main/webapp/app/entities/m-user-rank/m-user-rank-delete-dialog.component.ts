import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMUserRank } from 'app/shared/model/m-user-rank.model';
import { MUserRankService } from './m-user-rank.service';

@Component({
  selector: 'jhi-m-user-rank-delete-dialog',
  templateUrl: './m-user-rank-delete-dialog.component.html'
})
export class MUserRankDeleteDialogComponent {
  mUserRank: IMUserRank;

  constructor(protected mUserRankService: MUserRankService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mUserRankService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mUserRankListModification',
        content: 'Deleted an mUserRank'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-user-rank-delete-popup',
  template: ''
})
export class MUserRankDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUserRank }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MUserRankDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mUserRank = mUserRank;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-user-rank', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-user-rank', { outlets: { popup: null } }]);
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
