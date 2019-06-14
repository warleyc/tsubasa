import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';

@Component({
  selector: 'jhi-m-quest-stage-condition-detail',
  templateUrl: './m-quest-stage-condition-detail.component.html'
})
export class MQuestStageConditionDetailComponent implements OnInit {
  mQuestStageCondition: IMQuestStageCondition;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mQuestStageCondition }) => {
      this.mQuestStageCondition = mQuestStageCondition;
    });
  }

  previousState() {
    window.history.back();
  }
}
