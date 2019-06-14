import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetPlayableCardGroup } from 'app/shared/model/m-target-playable-card-group.model';
import { MTargetPlayableCardGroupService } from './m-target-playable-card-group.service';

@Component({
  selector: 'jhi-m-target-playable-card-group-delete-dialog',
  templateUrl: './m-target-playable-card-group-delete-dialog.component.html'
})
export class MTargetPlayableCardGroupDeleteDialogComponent {
  mTargetPlayableCardGroup: IMTargetPlayableCardGroup;

  constructor(
    protected mTargetPlayableCardGroupService: MTargetPlayableCardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetPlayableCardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetPlayableCardGroupListModification',
        content: 'Deleted an mTargetPlayableCardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-playable-card-group-delete-popup',
  template: ''
})
export class MTargetPlayableCardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetPlayableCardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetPlayableCardGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTargetPlayableCardGroup = mTargetPlayableCardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-playable-card-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-playable-card-group', { outlets: { popup: null } }]);
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
