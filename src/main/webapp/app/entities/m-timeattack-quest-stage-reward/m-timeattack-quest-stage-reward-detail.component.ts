import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-timeattack-quest-stage-reward-detail',
  templateUrl: './m-timeattack-quest-stage-reward-detail.component.html'
})
export class MTimeattackQuestStageRewardDetailComponent implements OnInit {
  mTimeattackQuestStageReward: IMTimeattackQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackQuestStageReward }) => {
      this.mTimeattackQuestStageReward = mTimeattackQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
