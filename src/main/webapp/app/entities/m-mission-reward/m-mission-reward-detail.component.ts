import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMissionReward } from 'app/shared/model/m-mission-reward.model';

@Component({
  selector: 'jhi-m-mission-reward-detail',
  templateUrl: './m-mission-reward-detail.component.html'
})
export class MMissionRewardDetailComponent implements OnInit {
  mMissionReward: IMMissionReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMissionReward }) => {
      this.mMissionReward = mMissionReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
