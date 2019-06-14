import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMInheritActionSkillCost } from 'app/shared/model/m-inherit-action-skill-cost.model';
import { MInheritActionSkillCostService } from './m-inherit-action-skill-cost.service';

@Component({
  selector: 'jhi-m-inherit-action-skill-cost-delete-dialog',
  templateUrl: './m-inherit-action-skill-cost-delete-dialog.component.html'
})
export class MInheritActionSkillCostDeleteDialogComponent {
  mInheritActionSkillCost: IMInheritActionSkillCost;

  constructor(
    protected mInheritActionSkillCostService: MInheritActionSkillCostService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mInheritActionSkillCostService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mInheritActionSkillCostListModification',
        content: 'Deleted an mInheritActionSkillCost'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-inherit-action-skill-cost-delete-popup',
  template: ''
})
export class MInheritActionSkillCostDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mInheritActionSkillCost }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MInheritActionSkillCostDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mInheritActionSkillCost = mInheritActionSkillCost;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-inherit-action-skill-cost', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-inherit-action-skill-cost', { outlets: { popup: null } }]);
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
