import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';
import { MActionSkillHolderCardContentService } from './m-action-skill-holder-card-content.service';

@Component({
  selector: 'jhi-m-action-skill-holder-card-content-delete-dialog',
  templateUrl: './m-action-skill-holder-card-content-delete-dialog.component.html'
})
export class MActionSkillHolderCardContentDeleteDialogComponent {
  mActionSkillHolderCardContent: IMActionSkillHolderCardContent;

  constructor(
    protected mActionSkillHolderCardContentService: MActionSkillHolderCardContentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mActionSkillHolderCardContentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mActionSkillHolderCardContentListModification',
        content: 'Deleted an mActionSkillHolderCardContent'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-action-skill-holder-card-content-delete-popup',
  template: ''
})
export class MActionSkillHolderCardContentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mActionSkillHolderCardContent }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MActionSkillHolderCardContentDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mActionSkillHolderCardContent = mActionSkillHolderCardContent;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-action-skill-holder-card-content', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-action-skill-holder-card-content', { outlets: { popup: null } }]);
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
