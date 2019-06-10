import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-challenge-quest-stage-reward-detail',
  templateUrl: './m-challenge-quest-stage-reward-detail.component.html'
})
export class MChallengeQuestStageRewardDetailComponent implements OnInit {
  mChallengeQuestStageReward: IMChallengeQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestStageReward }) => {
      this.mChallengeQuestStageReward = mChallengeQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
