import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';

@Component({
  selector: 'jhi-m-weekly-quest-tsubasa-point-reward-detail',
  templateUrl: './m-weekly-quest-tsubasa-point-reward-detail.component.html'
})
export class MWeeklyQuestTsubasaPointRewardDetailComponent implements OnInit {
  mWeeklyQuestTsubasaPointReward: IMWeeklyQuestTsubasaPointReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mWeeklyQuestTsubasaPointReward }) => {
      this.mWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
