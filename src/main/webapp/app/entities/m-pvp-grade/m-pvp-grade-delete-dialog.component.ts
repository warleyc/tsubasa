import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPvpGrade } from 'app/shared/model/m-pvp-grade.model';
import { MPvpGradeService } from './m-pvp-grade.service';

@Component({
  selector: 'jhi-m-pvp-grade-delete-dialog',
  templateUrl: './m-pvp-grade-delete-dialog.component.html'
})
export class MPvpGradeDeleteDialogComponent {
  mPvpGrade: IMPvpGrade;

  constructor(protected mPvpGradeService: MPvpGradeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPvpGradeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPvpGradeListModification',
        content: 'Deleted an mPvpGrade'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-pvp-grade-delete-popup',
  template: ''
})
export class MPvpGradeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpGrade }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPvpGradeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mPvpGrade = mPvpGrade;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-pvp-grade', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-pvp-grade', { outlets: { popup: null } }]);
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
