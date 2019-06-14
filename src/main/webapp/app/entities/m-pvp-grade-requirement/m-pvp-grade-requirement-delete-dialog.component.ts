import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpGradeRequirement } from 'app/shared/model/m-pvp-grade-requirement.model';
import { MPvpGradeRequirementService } from './m-pvp-grade-requirement.service';

@Component({
  selector: 'jhi-m-pvp-grade-requirement-delete-dialog',
  templateUrl: './m-pvp-grade-requirement-delete-dialog.component.html'
})
export class MPvpGradeRequirementDeleteDialogComponent {
  mPvpGradeRequirement: IMPvpGradeRequirement;

  constructor(
    protected mPvpGradeRequirementService: MPvpGradeRequirementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpGradeRequirementService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpGradeRequirementListModification',
        content: 'Deleted an mPvpGradeRequirement'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-grade-requirement-delete-popup',
  template: ''
})
export class MPvpGradeRequirementDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpGradeRequirement }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpGradeRequirementDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mPvpGradeRequirement = mPvpGradeRequirement;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-grade-requirement', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-grade-requirement', { outlets: { popup: null } }]);
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
