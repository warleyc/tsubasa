import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionTrajectoryCutIn } from 'app/shared/model/m-gacha-rendition-trajectory-cut-in.model';
import { MGachaRenditionTrajectoryCutInService } from './m-gacha-rendition-trajectory-cut-in.service';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-cut-in-delete-dialog',
  templateUrl: './m-gacha-rendition-trajectory-cut-in-delete-dialog.component.html'
})
export class MGachaRenditionTrajectoryCutInDeleteDialogComponent {
  mGachaRenditionTrajectoryCutIn: IMGachaRenditionTrajectoryCutIn;

  constructor(
    protected mGachaRenditionTrajectoryCutInService: MGachaRenditionTrajectoryCutInService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionTrajectoryCutInService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionTrajectoryCutInListModification',
        content: 'Deleted an mGachaRenditionTrajectoryCutIn'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-cut-in-delete-popup',
  template: ''
})
export class MGachaRenditionTrajectoryCutInDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectoryCutIn }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionTrajectoryCutInDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionTrajectoryCutIn = mGachaRenditionTrajectoryCutIn;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-trajectory-cut-in', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-trajectory-cut-in', { outlets: { popup: null } }]);
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
