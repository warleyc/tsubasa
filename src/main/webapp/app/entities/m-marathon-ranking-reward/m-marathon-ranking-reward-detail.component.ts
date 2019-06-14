import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonRankingReward } from 'app/shared/model/m-marathon-ranking-reward.model';

@Component({
  selector: 'jhi-m-marathon-ranking-reward-detail',
  templateUrl: './m-marathon-ranking-reward-detail.component.html'
})
export class MMarathonRankingRewardDetailComponent implements OnInit {
  mMarathonRankingReward: IMMarathonRankingReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonRankingReward }) => {
      this.mMarathonRankingReward = mMarathonRankingReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
