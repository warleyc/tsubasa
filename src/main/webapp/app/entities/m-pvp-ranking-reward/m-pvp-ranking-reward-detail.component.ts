import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';

@Component({
  selector: 'jhi-m-pvp-ranking-reward-detail',
  templateUrl: './m-pvp-ranking-reward-detail.component.html'
})
export class MPvpRankingRewardDetailComponent implements OnInit {
  mPvpRankingReward: IMPvpRankingReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpRankingReward }) => {
      this.mPvpRankingReward = mPvpRankingReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
