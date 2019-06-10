import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEncountersCut } from 'app/shared/model/m-encounters-cut.model';
import { MEncountersCutService } from './m-encounters-cut.service';

@Component({
  selector: 'jhi-m-encounters-cut-delete-dialog',
  templateUrl: './m-encounters-cut-delete-dialog.component.html'
})
export class MEncountersCutDeleteDialogComponent {
  mEncountersCut: IMEncountersCut;

  constructor(
    protected mEncountersCutService: MEncountersCutService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEncountersCutService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEncountersCutListModification',
        content: 'Deleted an mEncountersCut'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-encounters-cut-delete-popup',
  template: ''
})
export class MEncountersCutDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersCut }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEncountersCutDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mEncountersCut = mEncountersCut;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-encounters-cut', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-encounters-cut', { outlets: { popup: null } }]);
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
