import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMatchResultCutin } from 'app/shared/model/m-match-result-cutin.model';
import { MMatchResultCutinService } from './m-match-result-cutin.service';

@Component({
  selector: 'jhi-m-match-result-cutin-delete-dialog',
  templateUrl: './m-match-result-cutin-delete-dialog.component.html'
})
export class MMatchResultCutinDeleteDialogComponent {
  mMatchResultCutin: IMMatchResultCutin;

  constructor(
    protected mMatchResultCutinService: MMatchResultCutinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMatchResultCutinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMatchResultCutinListModification',
        content: 'Deleted an mMatchResultCutin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-match-result-cutin-delete-popup',
  template: ''
})
export class MMatchResultCutinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchResultCutin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMatchResultCutinDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMatchResultCutin = mMatchResultCutin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-match-result-cutin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-match-result-cutin', { outlets: { popup: null } }]);
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
