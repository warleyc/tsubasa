import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSituation } from 'app/shared/model/m-situation.model';
import { MSituationService } from './m-situation.service';

@Component({
  selector: 'jhi-m-situation-delete-dialog',
  templateUrl: './m-situation-delete-dialog.component.html'
})
export class MSituationDeleteDialogComponent {
  mSituation: IMSituation;

  constructor(
    protected mSituationService: MSituationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSituationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSituationListModification',
        content: 'Deleted an mSituation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-situation-delete-popup',
  template: ''
})
export class MSituationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSituation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSituationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mSituation = mSituation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-situation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-situation', { outlets: { popup: null } }]);
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
