import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMFormation } from 'app/shared/model/m-formation.model';
import { MFormationService } from './m-formation.service';

@Component({
  selector: 'jhi-m-formation-delete-dialog',
  templateUrl: './m-formation-delete-dialog.component.html'
})
export class MFormationDeleteDialogComponent {
  mFormation: IMFormation;

  constructor(
    protected mFormationService: MFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mFormationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mFormationListModification',
        content: 'Deleted an mFormation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-formation-delete-popup',
  template: ''
})
export class MFormationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mFormation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MFormationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mFormation = mFormation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-formation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-formation', { outlets: { popup: null } }]);
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
