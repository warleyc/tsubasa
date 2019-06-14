import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';

@Component({
  selector: 'jhi-m-quest-coop-reward-detail',
  templateUrl: './m-quest-coop-reward-detail.component.html'
})
export class MQuestCoopRewardDetailComponent implements OnInit {
  mQuestCoopReward: IMQuestCoopReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestCoopReward }) => {
      this.mQuestCoopReward = mQuestCoopReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
