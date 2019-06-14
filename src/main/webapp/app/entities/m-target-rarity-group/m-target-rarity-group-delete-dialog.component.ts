import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';
import { MTargetRarityGroupService } from './m-target-rarity-group.service';

@Component({
  selector: 'jhi-m-target-rarity-group-delete-dialog',
  templateUrl: './m-target-rarity-group-delete-dialog.component.html'
})
export class MTargetRarityGroupDeleteDialogComponent {
  mTargetRarityGroup: IMTargetRarityGroup;

  constructor(
    protected mTargetRarityGroupService: MTargetRarityGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mTargetRarityGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mTargetRarityGroupListModification',
        content: 'Deleted an mTargetRarityGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-target-rarity-group-delete-popup',
  template: ''
})
export class MTargetRarityGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTargetRarityGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MTargetRarityGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mTargetRarityGroup = mTargetRarityGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-target-rarity-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-target-rarity-group', { outlets: { popup: null } }]);
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
