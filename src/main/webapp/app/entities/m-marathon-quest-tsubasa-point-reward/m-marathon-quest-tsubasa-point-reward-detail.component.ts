import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonQuestTsubasaPointReward } from 'app/shared/model/m-marathon-quest-tsubasa-point-reward.model';

@Component({
  selector: 'jhi-m-marathon-quest-tsubasa-point-reward-detail',
  templateUrl: './m-marathon-quest-tsubasa-point-reward-detail.component.html'
})
export class MMarathonQuestTsubasaPointRewardDetailComponent implements OnInit {
  mMarathonQuestTsubasaPointReward: IMMarathonQuestTsubasaPointReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonQuestTsubasaPointReward }) => {
      this.mMarathonQuestTsubasaPointReward = mMarathonQuestTsubasaPointReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
