import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';

@Component({
  selector: 'jhi-m-guerilla-quest-tsubasa-point-reward-detail',
  templateUrl: './m-guerilla-quest-tsubasa-point-reward-detail.component.html'
})
export class MGuerillaQuestTsubasaPointRewardDetailComponent implements OnInit {
  mGuerillaQuestTsubasaPointReward: IMGuerillaQuestTsubasaPointReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuerillaQuestTsubasaPointReward }) => {
      this.mGuerillaQuestTsubasaPointReward = mGuerillaQuestTsubasaPointReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
