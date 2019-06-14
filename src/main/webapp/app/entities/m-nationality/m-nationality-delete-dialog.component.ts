import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMNationality } from 'app/shared/model/m-nationality.model';
import { MNationalityService } from './m-nationality.service';

@Component({
  selector: 'jhi-m-nationality-delete-dialog',
  templateUrl: './m-nationality-delete-dialog.component.html'
})
export class MNationalityDeleteDialogComponent {
  mNationality: IMNationality;

  constructor(
    protected mNationalityService: MNationalityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mNationalityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mNationalityListModification',
        content: 'Deleted an mNationality'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-nationality-delete-popup',
  template: ''
})
export class MNationalityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mNationality }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MNationalityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mNationality = mNationality;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-nationality', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-nationality', { outlets: { popup: null } }]);
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
