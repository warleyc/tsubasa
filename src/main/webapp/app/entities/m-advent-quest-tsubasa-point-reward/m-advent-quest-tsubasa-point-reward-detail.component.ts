import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';

@Component({
  selector: 'jhi-m-advent-quest-tsubasa-point-reward-detail',
  templateUrl: './m-advent-quest-tsubasa-point-reward-detail.component.html'
})
export class MAdventQuestTsubasaPointRewardDetailComponent implements OnInit {
  mAdventQuestTsubasaPointReward: IMAdventQuestTsubasaPointReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAdventQuestTsubasaPointReward }) => {
      this.mAdventQuestTsubasaPointReward = mAdventQuestTsubasaPointReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
