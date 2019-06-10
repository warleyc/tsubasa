import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';
import { MActionSkillHolderCardCtService } from './m-action-skill-holder-card-ct.service';

@Component({
  selector: 'jhi-m-action-skill-holder-card-ct-delete-dialog',
  templateUrl: './m-action-skill-holder-card-ct-delete-dialog.component.html'
})
export class MActionSkillHolderCardCtDeleteDialogComponent {
  mActionSkillHolderCardCt: IMActionSkillHolderCardCt;

  constructor(
    protected mActionSkillHolderCardCtService: MActionSkillHolderCardCtService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mActionSkillHolderCardCtService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mActionSkillHolderCardCtListModification',
        content: 'Deleted an mActionSkillHolderCardCt'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-action-skill-holder-card-ct-delete-popup',
  template: ''
})
export class MActionSkillHolderCardCtDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionSkillHolderCardCt }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MActionSkillHolderCardCtDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mActionSkillHolderCardCt = mActionSkillHolderCardCt;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-action-skill-holder-card-ct', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-action-skill-holder-card-ct', { outlets: { popup: null } }]);
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
