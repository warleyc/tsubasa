import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMDistributeCardParameter } from 'app/shared/model/m-distribute-card-parameter.model';

@Component({
  selector: 'jhi-m-distribute-card-parameter-detail',
  templateUrl: './m-distribute-card-parameter-detail.component.html'
})
export class MDistributeCardParameterDetailComponent implements OnInit {
  mDistributeCardParameter: IMDistributeCardParameter;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDistributeCardParameter }) => {
      this.mDistributeCardParameter = mDistributeCardParameter;
    });
  }

  previousState() {
    window.history.back();
  }
}
