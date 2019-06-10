import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';

@Component({
  selector: 'jhi-m-challenge-quest-achievement-reward-group-detail',
  templateUrl: './m-challenge-quest-achievement-reward-group-detail.component.html'
})
export class MChallengeQuestAchievementRewardGroupDetailComponent implements OnInit {
  mChallengeQuestAchievementRewardGroup: IMChallengeQuestAchievementRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mChallengeQuestAchievementRewardGroup }) => {
      this.mChallengeQuestAchievementRewardGroup = mChallengeQuestAchievementRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
