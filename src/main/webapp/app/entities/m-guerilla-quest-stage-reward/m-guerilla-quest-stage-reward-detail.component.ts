import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-guerilla-quest-stage-reward-detail',
  templateUrl: './m-guerilla-quest-stage-reward-detail.component.html'
})
export class MGuerillaQuestStageRewardDetailComponent implements OnInit {
  mGuerillaQuestStageReward: IMGuerillaQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestStageReward }) => {
      this.mGuerillaQuestStageReward = mGuerillaQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
