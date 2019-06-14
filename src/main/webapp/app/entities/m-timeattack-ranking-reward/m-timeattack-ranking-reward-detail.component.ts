import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-detail',
  templateUrl: './m-timeattack-ranking-reward-detail.component.html'
})
export class MTimeattackRankingRewardDetailComponent implements OnInit {
  mTimeattackRankingReward: IMTimeattackRankingReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackRankingReward }) => {
      this.mTimeattackRankingReward = mTimeattackRankingReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
