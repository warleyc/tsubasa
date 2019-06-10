import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-detail',
  templateUrl: './m-challenge-quest-achievement-reward-detail.component.html'
})
export class MChallengeQuestAchievementRewardDetailComponent implements OnInit {
  mChallengeQuestAchievementReward: IMChallengeQuestAchievementReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestAchievementReward }) => {
      this.mChallengeQuestAchievementReward = mChallengeQuestAchievementReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
