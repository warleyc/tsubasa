import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';
import { MGuerillaQuestWorldService } from './m-guerilla-quest-world.service';

@Component({
  selector: 'jhi-m-guerilla-quest-world-delete-dialog',
  templateUrl: './m-guerilla-quest-world-delete-dialog.component.html'
})
export class MGuerillaQuestWorldDeleteDialogComponent {
  mGuerillaQuestWorld: IMGuerillaQuestWorld;

  constructor(
    protected mGuerillaQuestWorldService: MGuerillaQuestWorldService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGuerillaQuestWorldService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGuerillaQuestWorldListModification',
        content: 'Deleted an mGuerillaQuestWorld'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-guerilla-quest-world-delete-popup',
  template: ''
})
export class MGuerillaQuestWorldDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestWorld }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGuerillaQuestWorldDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGuerillaQuestWorld = mGuerillaQuestWorld;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-guerilla-quest-world', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-guerilla-quest-world', { outlets: { popup: null } }]);
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
