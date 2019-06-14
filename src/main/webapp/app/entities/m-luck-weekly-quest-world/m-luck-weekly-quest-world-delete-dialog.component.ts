import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';
import { MLuckWeeklyQuestWorldService } from './m-luck-weekly-quest-world.service';

@Component({
  selector: 'jhi-m-luck-weekly-quest-world-delete-dialog',
  templateUrl: './m-luck-weekly-quest-world-delete-dialog.component.html'
})
export class MLuckWeeklyQuestWorldDeleteDialogComponent {
  mLuckWeeklyQuestWorld: IMLuckWeeklyQuestWorld;

  constructor(
    protected mLuckWeeklyQuestWorldService: MLuckWeeklyQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mLuckWeeklyQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mLuckWeeklyQuestWorldListModification',
        content: 'Deleted an mLuckWeeklyQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-luck-weekly-quest-world-delete-popup',
  template: ''
})
export class MLuckWeeklyQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MLuckWeeklyQuestWorldDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mLuckWeeklyQuestWorld = mLuckWeeklyQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-luck-weekly-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-luck-weekly-quest-world', { outlets: { popup: null } }]);
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
