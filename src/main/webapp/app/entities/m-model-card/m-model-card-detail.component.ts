import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMModelCard } from 'app/shared/model/m-model-card.model';

@Component({
  selector: 'jhi-m-model-card-detail',
  templateUrl: './m-model-card-detail.component.html'
})
export class MModelCardDetailComponent implements OnInit {
  mModelCard: IMModelCard;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelCard }) => {
      this.mModelCard = mModelCard;
    });
  }

  previousState() {
    window.history.back();
  }
}
