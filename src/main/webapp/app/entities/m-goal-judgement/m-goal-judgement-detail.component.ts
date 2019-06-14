import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGoalJudgement } from 'app/shared/model/m-goal-judgement.model';

@Component({
  selector: 'jhi-m-goal-judgement-detail',
  templateUrl: './m-goal-judgement-detail.component.html'
})
export class MGoalJudgementDetailComponent implements OnInit {
  mGoalJudgement: IMGoalJudgement;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGoalJudgement }) => {
      this.mGoalJudgement = mGoalJudgement;
    });
  }

  previousState() {
    window.history.back();
  }
}
