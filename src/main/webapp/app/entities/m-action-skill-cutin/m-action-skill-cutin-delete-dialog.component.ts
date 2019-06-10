import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';
import { MActionSkillCutinService } from './m-action-skill-cutin.service';

@Component({
  selector: 'jhi-m-action-skill-cutin-delete-dialog',
  templateUrl: './m-action-skill-cutin-delete-dialog.component.html'
})
export class MActionSkillCutinDeleteDialogComponent {
  mActionSkillCutin: IMActionSkillCutin;

  constructor(
    protected mActionSkillCutinService: MActionSkillCutinService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mActionSkillCutinService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mActionSkillCutinListModification',
        content: 'Deleted an mActionSkillCutin'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-action-skill-cutin-delete-popup',
  template: ''
})
export class MActionSkillCutinDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionSkillCutin }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MActionSkillCutinDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mActionSkillCutin = mActionSkillCutin;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-action-skill-cutin', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-action-skill-cutin', { outlets: { popup: null } }]);
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
