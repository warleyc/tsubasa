import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMarathonQuestWorld } from 'app/shared/model/m-marathon-quest-world.model';
import { MMarathonQuestWorldService } from './m-marathon-quest-world.service';

@Component({
  selector: 'jhi-m-marathon-quest-world-delete-dialog',
  templateUrl: './m-marathon-quest-world-delete-dialog.component.html'
})
export class MMarathonQuestWorldDeleteDialogComponent {
  mMarathonQuestWorld: IMMarathonQuestWorld;

  constructor(
    protected mMarathonQuestWorldService: MMarathonQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMarathonQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMarathonQuestWorldListModification',
        content: 'Deleted an mMarathonQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-marathon-quest-world-delete-popup',
  template: ''
})
export class MMarathonQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMarathonQuestWorldDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mMarathonQuestWorld = mMarathonQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-marathon-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-marathon-quest-world', { outlets: { popup: null } }]);
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
