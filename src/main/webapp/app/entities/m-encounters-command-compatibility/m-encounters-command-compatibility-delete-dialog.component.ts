import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEncountersCommandCompatibility } from 'app/shared/model/m-encounters-command-compatibility.model';
import { MEncountersCommandCompatibilityService } from './m-encounters-command-compatibility.service';

@Component({
  selector: 'jhi-m-encounters-command-compatibility-delete-dialog',
  templateUrl: './m-encounters-command-compatibility-delete-dialog.component.html'
})
export class MEncountersCommandCompatibilityDeleteDialogComponent {
  mEncountersCommandCompatibility: IMEncountersCommandCompatibility;

  constructor(
    protected mEncountersCommandCompatibilityService: MEncountersCommandCompatibilityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEncountersCommandCompatibilityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEncountersCommandCompatibilityListModification',
        content: 'Deleted an mEncountersCommandCompatibility'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-encounters-command-compatibility-delete-popup',
  template: ''
})
export class MEncountersCommandCompatibilityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersCommandCompatibility }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEncountersCommandCompatibilityDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mEncountersCommandCompatibility = mEncountersCommandCompatibility;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-encounters-command-compatibility', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-encounters-command-compatibility', { outlets: { popup: null } }]);
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
