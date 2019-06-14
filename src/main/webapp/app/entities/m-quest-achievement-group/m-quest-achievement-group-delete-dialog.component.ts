import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';
import { MQuestAchievementGroupService } from './m-quest-achievement-group.service';

@Component({
  selector: 'jhi-m-quest-achievement-group-delete-dialog',
  templateUrl: './m-quest-achievement-group-delete-dialog.component.html'
})
export class MQuestAchievementGroupDeleteDialogComponent {
  mQuestAchievementGroup: IMQuestAchievementGroup;

  constructor(
    protected mQuestAchievementGroupService: MQuestAchievementGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestAchievementGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestAchievementGroupListModification',
        content: 'Deleted an mQuestAchievementGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-achievement-group-delete-popup',
  template: ''
})
export class MQuestAchievementGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestAchievementGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestAchievementGroupDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestAchievementGroup = mQuestAchievementGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-achievement-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-achievement-group', { outlets: { popup: null } }]);
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
