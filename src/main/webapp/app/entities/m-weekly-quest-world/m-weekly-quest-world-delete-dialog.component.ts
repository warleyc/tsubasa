import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMWeeklyQuestWorld } from 'app/shared/model/m-weekly-quest-world.model';
import { MWeeklyQuestWorldService } from './m-weekly-quest-world.service';

@Component({
  selector: 'jhi-m-weekly-quest-world-delete-dialog',
  templateUrl: './m-weekly-quest-world-delete-dialog.component.html'
})
export class MWeeklyQuestWorldDeleteDialogComponent {
  mWeeklyQuestWorld: IMWeeklyQuestWorld;

  constructor(
    protected mWeeklyQuestWorldService: MWeeklyQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mWeeklyQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mWeeklyQuestWorldListModification',
        content: 'Deleted an mWeeklyQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-weekly-quest-world-delete-popup',
  template: ''
})
export class MWeeklyQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MWeeklyQuestWorldDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mWeeklyQuestWorld = mWeeklyQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-weekly-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-weekly-quest-world', { outlets: { popup: null } }]);
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
