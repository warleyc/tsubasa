import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';
import { MRecommendFormationFilterService } from './m-recommend-formation-filter.service';

@Component({
  selector: 'jhi-m-recommend-formation-filter-delete-dialog',
  templateUrl: './m-recommend-formation-filter-delete-dialog.component.html'
})
export class MRecommendFormationFilterDeleteDialogComponent {
  mRecommendFormationFilter: IMRecommendFormationFilter;

  constructor(
    protected mRecommendFormationFilterService: MRecommendFormationFilterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mRecommendFormationFilterService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mRecommendFormationFilterListModification',
        content: 'Deleted an mRecommendFormationFilter'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-recommend-formation-filter-delete-popup',
  template: ''
})
export class MRecommendFormationFilterDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRecommendFormationFilter }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MRecommendFormationFilterDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mRecommendFormationFilter = mRecommendFormationFilter;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-recommend-formation-filter', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-recommend-formation-filter', { outlets: { popup: null } }]);
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
