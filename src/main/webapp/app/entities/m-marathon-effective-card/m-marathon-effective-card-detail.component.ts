import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonEffectiveCard } from 'app/shared/model/m-marathon-effective-card.model';

@Component({
  selector: 'jhi-m-marathon-effective-card-detail',
  templateUrl: './m-marathon-effective-card-detail.component.html'
})
export class MMarathonEffectiveCardDetailComponent implements OnInit {
  mMarathonEffectiveCard: IMMarathonEffectiveCard;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonEffectiveCard }) => {
      this.mMarathonEffectiveCard = mMarathonEffectiveCard;
    });
  }

  previousState() {
    window.history.back();
  }
}
