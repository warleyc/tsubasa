import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMRivalEncountCutin } from 'app/shared/model/m-rival-encount-cutin.model';
import { MRivalEncountCutinService } from './m-rival-encount-cutin.service';

@Component({
  selector: 'jhi-m-rival-encount-cutin-delete-dialog',
  templateUrl: './m-rival-encount-cutin-delete-dialog.component.html'
})
export class MRivalEncountCutinDeleteDialogComponent {
  mRivalEncountCutin: IMRivalEncountCutin;

  constructor(
    protected mRivalEncountCutinService: MRivalEncountCutinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mRivalEncountCutinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mRivalEncountCutinListModification',
        content: 'Deleted an mRivalEncountCutin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-rival-encount-cutin-delete-popup',
  template: ''
})
export class MRivalEncountCutinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRivalEncountCutin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MRivalEncountCutinDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mRivalEncountCutin = mRivalEncountCutin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-rival-encount-cutin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-rival-encount-cutin', { outlets: { popup: null } }]);
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
