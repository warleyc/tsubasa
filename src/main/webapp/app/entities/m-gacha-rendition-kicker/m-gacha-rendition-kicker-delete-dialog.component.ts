import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';
import { MGachaRenditionKickerService } from './m-gacha-rendition-kicker.service';

@Component({
  selector: 'jhi-m-gacha-rendition-kicker-delete-dialog',
  templateUrl: './m-gacha-rendition-kicker-delete-dialog.component.html'
})
export class MGachaRenditionKickerDeleteDialogComponent {
  mGachaRenditionKicker: IMGachaRenditionKicker;

  constructor(
    protected mGachaRenditionKickerService: MGachaRenditionKickerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mGachaRenditionKickerService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mGachaRenditionKickerListModification',
        content: 'Deleted an mGachaRenditionKicker'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-gacha-rendition-kicker-delete-popup',
  template: ''
})
export class MGachaRenditionKickerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionKicker }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MGachaRenditionKickerDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mGachaRenditionKicker = mGachaRenditionKicker;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-gacha-rendition-kicker', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-gacha-rendition-kicker', { outlets: { popup: null } }]);
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
