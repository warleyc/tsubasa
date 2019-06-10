import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCardLevel } from 'app/shared/model/m-card-level.model';
import { MCardLevelService } from './m-card-level.service';

@Component({
  selector: 'jhi-m-card-level-delete-dialog',
  templateUrl: './m-card-level-delete-dialog.component.html'
})
export class MCardLevelDeleteDialogComponent {
  mCardLevel: IMCardLevel;

  constructor(
    protected mCardLevelService: MCardLevelService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCardLevelService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCardLevelListModification',
        content: 'Deleted an mCardLevel'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-card-level-delete-popup',
  template: ''
})
export class MCardLevelDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardLevel }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCardLevelDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mCardLevel = mCardLevel;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-card-level', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-card-level', { outlets: { popup: null } }]);
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
