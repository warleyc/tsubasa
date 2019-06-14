import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';

@Component({
  selector: 'jhi-m-pvp-ranking-reward-group-detail',
  templateUrl: './m-pvp-ranking-reward-group-detail.component.html'
})
export class MPvpRankingRewardGroupDetailComponent implements OnInit {
  mPvpRankingRewardGroup: IMPvpRankingRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpRankingRewardGroup }) => {
      this.mPvpRankingRewardGroup = mPvpRankingRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
