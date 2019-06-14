import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonLoopRewardGroup } from 'app/shared/model/m-marathon-loop-reward-group.model';

@Component({
  selector: 'jhi-m-marathon-loop-reward-group-detail',
  templateUrl: './m-marathon-loop-reward-group-detail.component.html'
})
export class MMarathonLoopRewardGroupDetailComponent implements OnInit {
  mMarathonLoopRewardGroup: IMMarathonLoopRewardGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonLoopRewardGroup }) => {
      this.mMarathonLoopRewardGroup = mMarathonLoopRewardGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
