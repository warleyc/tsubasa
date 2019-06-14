import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMSituationAnnounce } from 'app/shared/model/m-situation-announce.model';
import { MSituationAnnounceService } from './m-situation-announce.service';

@Component({
  selector: 'jhi-m-situation-announce-delete-dialog',
  templateUrl: './m-situation-announce-delete-dialog.component.html'
})
export class MSituationAnnounceDeleteDialogComponent {
  mSituationAnnounce: IMSituationAnnounce;

  constructor(
    protected mSituationAnnounceService: MSituationAnnounceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mSituationAnnounceService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mSituationAnnounceListModification',
        content: 'Deleted an mSituationAnnounce'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-situation-announce-delete-popup',
  template: ''
})
export class MSituationAnnounceDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSituationAnnounce }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MSituationAnnounceDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mSituationAnnounce = mSituationAnnounce;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-situation-announce', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-situation-announce', { outlets: { popup: null } }]);
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
