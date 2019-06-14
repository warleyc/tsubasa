import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';
import { MQuestRewardGroupService } from './m-quest-reward-group.service';

@Component({
  selector: 'jhi-m-quest-reward-group-delete-dialog',
  templateUrl: './m-quest-reward-group-delete-dialog.component.html'
})
export class MQuestRewardGroupDeleteDialogComponent {
  mQuestRewardGroup: IMQuestRewardGroup;

  constructor(
    protected mQuestRewardGroupService: MQuestRewardGroupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestRewardGroupService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestRewardGroupListModification',
        content: 'Deleted an mQuestRewardGroup'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-reward-group-delete-popup',
  template: ''
})
export class MQuestRewardGroupDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestRewardGroup }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestRewardGroupDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mQuestRewardGroup = mQuestRewardGroup;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-reward-group', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-reward-group', { outlets: { popup: null } }]);
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
