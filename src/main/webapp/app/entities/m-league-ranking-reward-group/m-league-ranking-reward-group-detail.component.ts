import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';

@Component({
  selector: 'jhi-m-league-ranking-reward-group-detail',
  templateUrl: './m-league-ranking-reward-group-detail.component.html'
})
export class MLeagueRankingRewardGroupDetailComponent implements OnInit {
  mLeagueRankingRewardGroup: IMLeagueRankingRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueRankingRewardGroup }) => {
      this.mLeagueRankingRewardGroup = mLeagueRankingRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
