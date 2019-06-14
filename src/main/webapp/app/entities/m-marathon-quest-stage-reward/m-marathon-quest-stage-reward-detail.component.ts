import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-marathon-quest-stage-reward-detail',
  templateUrl: './m-marathon-quest-stage-reward-detail.component.html'
})
export class MMarathonQuestStageRewardDetailComponent implements OnInit {
  mMarathonQuestStageReward: IMMarathonQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestStageReward }) => {
      this.mMarathonQuestStageReward = mMarathonQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
