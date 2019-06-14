import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMStoryResourceMapping } from 'app/shared/model/m-story-resource-mapping.model';
import { MStoryResourceMappingService } from './m-story-resource-mapping.service';

@Component({
  selector: 'jhi-m-story-resource-mapping-delete-dialog',
  templateUrl: './m-story-resource-mapping-delete-dialog.component.html'
})
export class MStoryResourceMappingDeleteDialogComponent {
  mStoryResourceMapping: IMStoryResourceMapping;

  constructor(
    protected mStoryResourceMappingService: MStoryResourceMappingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mStoryResourceMappingService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mStoryResourceMappingListModification',
        content: 'Deleted an mStoryResourceMapping'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-story-resource-mapping-delete-popup',
  template: ''
})
export class MStoryResourceMappingDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStoryResourceMapping }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MStoryResourceMappingDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mStoryResourceMapping = mStoryResourceMapping;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-story-resource-mapping', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-story-resource-mapping', { outlets: { popup: null } }]);
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
