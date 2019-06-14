import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestSpecialReward } from 'app/shared/model/m-quest-special-reward.model';

@Component({
  selector: 'jhi-m-quest-special-reward-detail',
  templateUrl: './m-quest-special-reward-detail.component.html'
})
export class MQuestSpecialRewardDetailComponent implements OnInit {
  mQuestSpecialReward: IMQuestSpecialReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestSpecialReward }) => {
      this.mQuestSpecialReward = mQuestSpecialReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
