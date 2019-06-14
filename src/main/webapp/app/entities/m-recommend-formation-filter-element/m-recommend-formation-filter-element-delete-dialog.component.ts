import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';
import { MRecommendFormationFilterElementService } from './m-recommend-formation-filter-element.service';

@Component({
  selector: 'jhi-m-recommend-formation-filter-element-delete-dialog',
  templateUrl: './m-recommend-formation-filter-element-delete-dialog.component.html'
})
export class MRecommendFormationFilterElementDeleteDialogComponent {
  mRecommendFormationFilterElement: IMRecommendFormationFilterElement;

  constructor(
    protected mRecommendFormationFilterElementService: MRecommendFormationFilterElementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mRecommendFormationFilterElementService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mRecommendFormationFilterElementListModification',
        content: 'Deleted an mRecommendFormationFilterElement'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-recommend-formation-filter-element-delete-popup',
  template: ''
})
export class MRecommendFormationFilterElementDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRecommendFormationFilterElement }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MRecommendFormationFilterElementDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mRecommendFormationFilterElement = mRecommendFormationFilterElement;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-recommend-formation-filter-element', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-recommend-formation-filter-element', { outlets: { popup: null } }]);
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
