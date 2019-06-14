import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';

@Component({
  selector: 'jhi-m-quest-clear-reward-weight-detail',
  templateUrl: './m-quest-clear-reward-weight-detail.component.html'
})
export class MQuestClearRewardWeightDetailComponent implements OnInit {
  mQuestClearRewardWeight: IMQuestClearRewardWeight;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestClearRewardWeight }) => {
      this.mQuestClearRewardWeight = mQuestClearRewardWeight;
    });
  }

  previousState() {
    window.history.back();
  }
}
