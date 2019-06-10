import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';

@Component({
  selector: 'jhi-m-distribute-card-parameter-step-detail',
  templateUrl: './m-distribute-card-parameter-step-detail.component.html'
})
export class MDistributeCardParameterStepDetailComponent implements OnInit {
  mDistributeCardParameterStep: IMDistributeCardParameterStep;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDistributeCardParameterStep }) => {
      this.mDistributeCardParameterStep = mDistributeCardParameterStep;
    });
  }

  previousState() {
    window.history.back();
  }
}
