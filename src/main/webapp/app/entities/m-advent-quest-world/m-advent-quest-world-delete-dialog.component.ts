import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMAdventQuestWorld } from 'app/shared/model/m-advent-quest-world.model';
import { MAdventQuestWorldService } from './m-advent-quest-world.service';

@Component({
  selector: 'jhi-m-advent-quest-world-delete-dialog',
  templateUrl: './m-advent-quest-world-delete-dialog.component.html'
})
export class MAdventQuestWorldDeleteDialogComponent {
  mAdventQuestWorld: IMAdventQuestWorld;

  constructor(
    protected mAdventQuestWorldService: MAdventQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mAdventQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mAdventQuestWorldListModification',
        content: 'Deleted an mAdventQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-advent-quest-world-delete-popup',
  template: ''
})
export class MAdventQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MAdventQuestWorldDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mAdventQuestWorld = mAdventQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-advent-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-advent-quest-world', { outlets: { popup: null } }]);
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
