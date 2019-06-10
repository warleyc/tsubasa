import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMActionLevel } from 'app/shared/model/m-action-level.model';
import { MActionLevelService } from './m-action-level.service';

@Component({
  selector: 'jhi-m-action-level-delete-dialog',
  templateUrl: './m-action-level-delete-dialog.component.html'
})
export class MActionLevelDeleteDialogComponent {
  mActionLevel: IMActionLevel;

  constructor(
    protected mActionLevelService: MActionLevelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mActionLevelService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mActionLevelListModification',
        content: 'Deleted an mActionLevel'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-action-level-delete-popup',
  template: ''
})
export class MActionLevelDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionLevel }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MActionLevelDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mActionLevel = mActionLevel;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-action-level', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-action-level', { outlets: { popup: null } }]);
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
