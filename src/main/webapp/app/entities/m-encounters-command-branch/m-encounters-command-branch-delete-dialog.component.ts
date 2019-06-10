import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';
import { MEncountersCommandBranchService } from './m-encounters-command-branch.service';

@Component({
  selector: 'jhi-m-encounters-command-branch-delete-dialog',
  templateUrl: './m-encounters-command-branch-delete-dialog.component.html'
})
export class MEncountersCommandBranchDeleteDialogComponent {
  mEncountersCommandBranch: IMEncountersCommandBranch;

  constructor(
    protected mEncountersCommandBranchService: MEncountersCommandBranchService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mEncountersCommandBranchService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mEncountersCommandBranchListModification',
        content: 'Deleted an mEncountersCommandBranch'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-encounters-command-branch-delete-popup',
  template: ''
})
export class MEncountersCommandBranchDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEncountersCommandBranch }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MEncountersCommandBranchDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mEncountersCommandBranch = mEncountersCommandBranch;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-encounters-command-branch', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-encounters-command-branch', { outlets: { popup: null } }]);
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
