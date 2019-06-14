import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMedal } from 'app/shared/model/m-medal.model';
import { MMedalService } from './m-medal.service';

@Component({
  selector: 'jhi-m-medal-delete-dialog',
  templateUrl: './m-medal-delete-dialog.component.html'
})
export class MMedalDeleteDialogComponent {
  mMedal: IMMedal;

  constructor(protected mMedalService: MMedalService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMedalService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMedalListModification',
        content: 'Deleted an mMedal'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-medal-delete-popup',
  template: ''
})
export class MMedalDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMedal }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMedalDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMedal = mMedal;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-medal', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-medal', { outlets: { popup: null } }]);
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
