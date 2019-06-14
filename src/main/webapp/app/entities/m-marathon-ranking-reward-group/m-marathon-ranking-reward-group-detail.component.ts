import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonRankingRewardGroup } from 'app/shared/model/m-marathon-ranking-reward-group.model';

@Component({
  selector: 'jhi-m-marathon-ranking-reward-group-detail',
  templateUrl: './m-marathon-ranking-reward-group-detail.component.html'
})
export class MMarathonRankingRewardGroupDetailComponent implements OnInit {
  mMarathonRankingRewardGroup: IMMarathonRankingRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonRankingRewardGroup }) => {
      this.mMarathonRankingRewardGroup = mMarathonRankingRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
