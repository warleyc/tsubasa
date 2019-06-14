import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMPowerupActionSkillCost } from 'app/shared/model/m-powerup-action-skill-cost.model';
import { MPowerupActionSkillCostService } from './m-powerup-action-skill-cost.service';

@Component({
  selector: 'jhi-m-powerup-action-skill-cost-delete-dialog',
  templateUrl: './m-powerup-action-skill-cost-delete-dialog.component.html'
})
export class MPowerupActionSkillCostDeleteDialogComponent {
  mPowerupActionSkillCost: IMPowerupActionSkillCost;

  constructor(
    protected mPowerupActionSkillCostService: MPowerupActionSkillCostService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mPowerupActionSkillCostService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mPowerupActionSkillCostListModification',
        content: 'Deleted an mPowerupActionSkillCost'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-powerup-action-skill-cost-delete-popup',
  template: ''
})
export class MPowerupActionSkillCostDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPowerupActionSkillCost }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MPowerupActionSkillCostDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mPowerupActionSkillCost = mPowerupActionSkillCost;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-powerup-action-skill-cost', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-powerup-action-skill-cost', { outlets: { popup: null } }]);
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
