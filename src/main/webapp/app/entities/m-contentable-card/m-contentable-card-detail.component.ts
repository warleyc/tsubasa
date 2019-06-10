import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMContentableCard } from 'app/shared/model/m-contentable-card.model';

@Component({
  selector: 'jhi-m-contentable-card-detail',
  templateUrl: './m-contentable-card-detail.component.html'
})
export class MContentableCardDetailComponent implements OnInit {
  mContentableCard: IMContentableCard;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mContentableCard }) => {
      this.mContentableCard = mContentableCard;
    });
  }

  previousState() {
    window.history.back();
  }
}
