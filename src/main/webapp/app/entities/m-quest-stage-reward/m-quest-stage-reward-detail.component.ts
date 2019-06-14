import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-quest-stage-reward-detail',
  templateUrl: './m-quest-stage-reward-detail.component.html'
})
export class MQuestStageRewardDetailComponent implements OnInit {
  mQuestStageReward: IMQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStageReward }) => {
      this.mQuestStageReward = mQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
