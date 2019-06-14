import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';

@Component({
  selector: 'jhi-m-quest-drop-rate-campaign-content-detail',
  templateUrl: './m-quest-drop-rate-campaign-content-detail.component.html'
})
export class MQuestDropRateCampaignContentDetailComponent implements OnInit {
  mQuestDropRateCampaignContent: IMQuestDropRateCampaignContent;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestDropRateCampaignContent }) => {
      this.mQuestDropRateCampaignContent = mQuestDropRateCampaignContent;
    });
  }

  previousState() {
    window.history.back();
  }
}
