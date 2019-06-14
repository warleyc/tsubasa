import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonLoopReward } from 'app/shared/model/m-marathon-loop-reward.model';

@Component({
  selector: 'jhi-m-marathon-loop-reward-detail',
  templateUrl: './m-marathon-loop-reward-detail.component.html'
})
export class MMarathonLoopRewardDetailComponent implements OnInit {
  mMarathonLoopReward: IMMarathonLoopReward;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonLoopReward }) => {
      this.mMarathonLoopReward = mMarathonLoopReward;
    });
  }

  previousState() {
    window.history.back();
  }
}
