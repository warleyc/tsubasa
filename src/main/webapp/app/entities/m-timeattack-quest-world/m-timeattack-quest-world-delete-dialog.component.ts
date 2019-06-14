import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTimeattackQuestWorld } from 'app/shared/model/m-timeattack-quest-world.model';
import { MTimeattackQuestWorldService } from './m-timeattack-quest-world.service';

@Component({
  selector: 'jhi-m-timeattack-quest-world-delete-dialog',
  templateUrl: './m-timeattack-quest-world-delete-dialog.component.html'
})
export class MTimeattackQuestWorldDeleteDialogComponent {
  mTimeattackQuestWorld: IMTimeattackQuestWorld;

  constructor(
    protected mTimeattackQuestWorldService: MTimeattackQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTimeattackQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTimeattackQuestWorldListModification',
        content: 'Deleted an mTimeattackQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-timeattack-quest-world-delete-popup',
  template: ''
})
export class MTimeattackQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTimeattackQuestWorldDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTimeattackQuestWorld = mTimeattackQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-timeattack-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-timeattack-quest-world', { outlets: { popup: null } }]);
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
