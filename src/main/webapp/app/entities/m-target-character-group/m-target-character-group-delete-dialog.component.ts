import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';
import { MTargetCharacterGroupService } from './m-target-character-group.service';

@Component({
  selector: 'jhi-m-target-character-group-delete-dialog',
  templateUrl: './m-target-character-group-delete-dialog.component.html'
})
export class MTargetCharacterGroupDeleteDialogComponent {
  mTargetCharacterGroup: IMTargetCharacterGroup;

  constructor(
    protected mTargetCharacterGroupService: MTargetCharacterGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetCharacterGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetCharacterGroupListModification',
        content: 'Deleted an mTargetCharacterGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-character-group-delete-popup',
  template: ''
})
export class MTargetCharacterGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetCharacterGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetCharacterGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mTargetCharacterGroup = mTargetCharacterGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-character-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-character-group', { outlets: { popup: null } }]);
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
