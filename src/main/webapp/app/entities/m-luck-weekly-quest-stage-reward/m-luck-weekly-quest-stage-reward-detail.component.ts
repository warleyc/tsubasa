import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-luck-weekly-quest-stage-reward-detail',
  templateUrl: './m-luck-weekly-quest-stage-reward-detail.component.html'
})
export class MLuckWeeklyQuestStageRewardDetailComponent implements OnInit {
  mLuckWeeklyQuestStageReward: IMLuckWeeklyQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckWeeklyQuestStageReward }) => {
      this.mLuckWeeklyQuestStageReward = mLuckWeeklyQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
