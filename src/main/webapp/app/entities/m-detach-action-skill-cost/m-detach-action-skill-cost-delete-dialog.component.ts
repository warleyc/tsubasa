import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMDetachActionSkillCost } from 'app/shared/model/m-detach-action-skill-cost.model';
import { MDetachActionSkillCostService } from './m-detach-action-skill-cost.service';

@Component({
  selector: 'jhi-m-detach-action-skill-cost-delete-dialog',
  templateUrl: './m-detach-action-skill-cost-delete-dialog.component.html'
})
export class MDetachActionSkillCostDeleteDialogComponent {
  mDetachActionSkillCost: IMDetachActionSkillCost;

  constructor(
    protected mDetachActionSkillCostService: MDetachActionSkillCostService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mDetachActionSkillCostService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mDetachActionSkillCostListModification',
        content: 'Deleted an mDetachActionSkillCost'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-detach-action-skill-cost-delete-popup',
  template: ''
})
export class MDetachActionSkillCostDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDetachActionSkillCost }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MDetachActionSkillCostDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mDetachActionSkillCost = mDetachActionSkillCost;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-detach-action-skill-cost', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-detach-action-skill-cost', { outlets: { popup: null } }]);
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
