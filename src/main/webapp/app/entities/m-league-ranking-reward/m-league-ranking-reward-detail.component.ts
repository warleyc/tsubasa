import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLeagueRankingReward } from 'app/shared/model/m-league-ranking-reward.model';

@Component({
  selector: 'jhi-m-league-ranking-reward-detail',
  templateUrl: './m-league-ranking-reward-detail.component.html'
})
export class MLeagueRankingRewardDetailComponent implements OnInit {
  mLeagueRankingReward: IMLeagueRankingReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueRankingReward }) => {
      this.mLeagueRankingReward = mLeagueRankingReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
