import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGoalEffectiveCard } from 'app/shared/model/m-goal-effective-card.model';

@Component({
  selector: 'jhi-m-goal-effective-card-detail',
  templateUrl: './m-goal-effective-card-detail.component.html'
})
export class MGoalEffectiveCardDetailComponent implements OnInit {
  mGoalEffectiveCard: IMGoalEffectiveCard;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGoalEffectiveCard }) => {
      this.mGoalEffectiveCard = mGoalEffectiveCard;
    });
  }

  previousState() {
    window.history.back();
  }
}
