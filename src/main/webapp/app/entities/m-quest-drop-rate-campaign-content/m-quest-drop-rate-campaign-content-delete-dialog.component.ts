import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';
import { MQuestDropRateCampaignContentService } from './m-quest-drop-rate-campaign-content.service';

@Component({
  selector: 'jhi-m-quest-drop-rate-campaign-content-delete-dialog',
  templateUrl: './m-quest-drop-rate-campaign-content-delete-dialog.component.html'
})
export class MQuestDropRateCampaignContentDeleteDialogComponent {
  mQuestDropRateCampaignContent: IMQuestDropRateCampaignContent;

  constructor(
    protected mQuestDropRateCampaignContentService: MQuestDropRateCampaignContentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mQuestDropRateCampaignContentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mQuestDropRateCampaignContentListModification',
        content: 'Deleted an mQuestDropRateCampaignContent'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-m-quest-drop-rate-campaign-content-delete-popup',
  template: ''
})
export class MQuestDropRateCampaignContentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestDropRateCampaignContent }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MQuestDropRateCampaignContentDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.mQuestDropRateCampaignContent = mQuestDropRateCampaignContent;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/m-quest-drop-rate-campaign-content', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/m-quest-drop-rate-campaign-content', { outlets: { popup: null } }]);
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
