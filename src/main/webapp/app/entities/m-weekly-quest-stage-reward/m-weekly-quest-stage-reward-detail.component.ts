import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-weekly-quest-stage-reward-detail',
  templateUrl: './m-weekly-quest-stage-reward-detail.component.html'
})
export class MWeeklyQuestStageRewardDetailComponent implements OnInit {
  mWeeklyQuestStageReward: IMWeeklyQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestStageReward }) => {
      this.mWeeklyQuestStageReward = mWeeklyQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
