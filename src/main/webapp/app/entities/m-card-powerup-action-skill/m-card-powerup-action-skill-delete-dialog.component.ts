import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';
import { MCardPowerupActionSkillService } from './m-card-powerup-action-skill.service';

@Component({
  selector: 'jhi-m-card-powerup-action-skill-delete-dialog',
  templateUrl: './m-card-powerup-action-skill-delete-dialog.component.html'
})
export class MCardPowerupActionSkillDeleteDialogComponent {
  mCardPowerupActionSkill: IMCardPowerupActionSkill;

  constructor(
    protected mCardPowerupActionSkillService: MCardPowerupActionSkillService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mCardPowerupActionSkillService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mCardPowerupActionSkillListModification',
        content: 'Deleted an mCardPowerupActionSkill'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-card-powerup-action-skill-delete-popup',
  template: ''
})
export class MCardPowerupActionSkillDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardPowerupActionSkill }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MCardPowerupActionSkillDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mCardPowerupActionSkill = mCardPowerupActionSkill;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-card-powerup-action-skill', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-card-powerup-action-skill', { outlets: { popup: null } }]);
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
