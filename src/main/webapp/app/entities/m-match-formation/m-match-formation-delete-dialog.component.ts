import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMMatchFormation } from 'app/shared/model/m-match-formation.model';
import { MMatchFormationService } from './m-match-formation.service';

@Component({
  selector: 'jhi-m-match-formation-delete-dialog',
  templateUrl: './m-match-formation-delete-dialog.component.html'
})
export class MMatchFormationDeleteDialogComponent {
  mMatchFormation: IMMatchFormation;

  constructor(
    protected mMatchFormationService: MMatchFormationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mMatchFormationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mMatchFormationListModification',
        content: 'Deleted an mMatchFormation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-match-formation-delete-popup',
  template: ''
})
export class MMatchFormationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMatchFormation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MMatchFormationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mMatchFormation = mMatchFormation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-match-formation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-match-formation', { outlets: { popup: null } }]);
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
