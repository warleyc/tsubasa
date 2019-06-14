import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';

@Component({
  selector: 'jhi-m-league-affiliate-reward-detail',
  templateUrl: './m-league-affiliate-reward-detail.component.html'
})
export class MLeagueAffiliateRewardDetailComponent implements OnInit {
  mLeagueAffiliateReward: IMLeagueAffiliateReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLeagueAffiliateReward }) => {
      this.mLeagueAffiliateReward = mLeagueAffiliateReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
