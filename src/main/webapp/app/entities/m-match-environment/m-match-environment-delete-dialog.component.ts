import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMatchEnvironment } from 'app/shared/model/m-match-environment.model';
import { MMatchEnvironmentService } from './m-match-environment.service';

@Component({
  selector: 'jhi-m-match-environment-delete-dialog',
  templateUrl: './m-match-environment-delete-dialog.component.html'
})
export class MMatchEnvironmentDeleteDialogComponent {
  mMatchEnvironment: IMMatchEnvironment;

  constructor(
    protected mMatchEnvironmentService: MMatchEnvironmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMatchEnvironmentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMatchEnvironmentListModification',
        content: 'Deleted an mMatchEnvironment'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-match-environment-delete-popup',
  template: ''
})
export class MMatchEnvironmentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchEnvironment }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMatchEnvironmentDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMatchEnvironment = mMatchEnvironment;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-match-environment', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-match-environment', { outlets: { popup: null } }]);
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
