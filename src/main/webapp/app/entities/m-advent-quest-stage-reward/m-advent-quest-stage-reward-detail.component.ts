import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';

@Component({
  selector: 'jhi-m-advent-quest-stage-reward-detail',
  templateUrl: './m-advent-quest-stage-reward-detail.component.html'
})
export class MAdventQuestStageRewardDetailComponent implements OnInit {
  mAdventQuestStageReward: IMAdventQuestStageReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestStageReward }) => {
      this.mAdventQuestStageReward = mAdventQuestStageReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
