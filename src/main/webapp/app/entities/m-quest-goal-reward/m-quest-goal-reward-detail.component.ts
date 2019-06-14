import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';

@Component({
  selector: 'jhi-m-quest-goal-reward-detail',
  templateUrl: './m-quest-goal-reward-detail.component.html'
})
export class MQuestGoalRewardDetailComponent implements OnInit {
  mQuestGoalReward: IMQuestGoalReward;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestGoalReward }) => {
      this.mQuestGoalReward = mQuestGoalReward;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
