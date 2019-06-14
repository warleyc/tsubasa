import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTicketQuestStageReward } from 'app/shared/model/m-ticket-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-ticket-quest-stage-reward-detail',
  templateUrl: './m-ticket-quest-stage-reward-detail.component.html'
})
export class MTicketQuestStageRewardDetailComponent implements OnInit {
  mTicketQuestStageReward: IMTicketQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTicketQuestStageReward }) => {
      this.mTicketQuestStageReward = mTicketQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
