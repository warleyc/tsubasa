import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';

@Component({
  selector: 'jhi-m-regulated-league-ranking-reward-detail',
  templateUrl: './m-regulated-league-ranking-reward-detail.component.html'
})
export class MRegulatedLeagueRankingRewardDetailComponent implements OnInit {
  mRegulatedLeagueRankingReward: IMRegulatedLeagueRankingReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRegulatedLeagueRankingReward }) => {
      this.mRegulatedLeagueRankingReward = mRegulatedLeagueRankingReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
