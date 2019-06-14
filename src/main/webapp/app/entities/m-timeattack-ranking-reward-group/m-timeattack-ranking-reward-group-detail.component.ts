import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';

@Component({
  selector: 'jhi-m-timeattack-ranking-reward-group-detail',
  templateUrl: './m-timeattack-ranking-reward-group-detail.component.html'
})
export class MTimeattackRankingRewardGroupDetailComponent implements OnInit {
  mTimeattackRankingRewardGroup: IMTimeattackRankingRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTimeattackRankingRewardGroup }) => {
      this.mTimeattackRankingRewardGroup = mTimeattackRankingRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
