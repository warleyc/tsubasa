import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonAchievementRewardGroup } from 'app/shared/model/m-marathon-achievement-reward-group.model';

@Component({
  selector: 'jhi-m-marathon-achievement-reward-group-detail',
  templateUrl: './m-marathon-achievement-reward-group-detail.component.html'
})
export class MMarathonAchievementRewardGroupDetailComponent implements OnInit {
  mMarathonAchievementRewardGroup: IMMarathonAchievementRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonAchievementRewardGroup }) => {
      this.mMarathonAchievementRewardGroup = mMarathonAchievementRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
