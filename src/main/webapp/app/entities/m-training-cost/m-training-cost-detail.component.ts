import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTrainingCost } from 'app/shared/model/m-training-cost.model';

@Component({
  selector: 'jhi-m-training-cost-detail',
  templateUrl: './m-training-cost-detail.component.html'
})
export class MTrainingCostDetailComponent implements OnInit {
  mTrainingCost: IMTrainingCost;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTrainingCost }) => {
      this.mTrainingCost = mTrainingCost;
    });
  }

  previousState() {
    window.history.back();
  }
}
