import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMStageStory } from 'app/shared/model/m-stage-story.model';
import { MStageStoryService } from './m-stage-story.service';

@Component({
  selector: 'jhi-m-stage-story-delete-dialog',
  templateUrl: './m-stage-story-delete-dialog.component.html'
})
export class MStageStoryDeleteDialogComponent {
  mStageStory: IMStageStory;

  constructor(
    protected mStageStoryService: MStageStoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mStageStoryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mStageStoryListModification',
        content: 'Deleted an mStageStory'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-stage-story-delete-popup',
  template: ''
})
export class MStageStoryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStageStory }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MStageStoryDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mStageStory = mStageStory;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-stage-story', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-stage-story', { outlets: { popup: null } }]);
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
