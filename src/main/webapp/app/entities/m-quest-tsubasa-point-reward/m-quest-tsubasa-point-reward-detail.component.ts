import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestTsubasaPointReward } from 'app/shared/model/m-quest-tsubasa-point-reward.model';

@Component({
  selector: 'jhi-m-quest-tsubasa-point-reward-detail',
  templateUrl: './m-quest-tsubasa-point-reward-detail.component.html'
})
export class MQuestTsubasaPointRewardDetailComponent implements OnInit {
  mQuestTsubasaPointReward: IMQuestTsubasaPointReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestTsubasaPointReward }) => {
      this.mQuestTsubasaPointReward = mQuestTsubasaPointReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
