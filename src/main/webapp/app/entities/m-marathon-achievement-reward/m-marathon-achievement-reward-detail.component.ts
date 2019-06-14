import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonAchievementReward } from 'app/shared/model/m-marathon-achievement-reward.model';

@Component({
  selector: 'jhi-m-marathon-achievement-reward-detail',
  templateUrl: './m-marathon-achievement-reward-detail.component.html'
})
export class MMarathonAchievementRewardDetailComponent implements OnInit {
  mMarathonAchievementReward: IMMarathonAchievementReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonAchievementReward }) => {
      this.mMarathonAchievementReward = mMarathonAchievementReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
