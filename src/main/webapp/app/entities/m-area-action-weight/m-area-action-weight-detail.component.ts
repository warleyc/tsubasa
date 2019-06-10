import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';

@Component({
  selector: 'jhi-m-area-action-weight-detail',
  templateUrl: './m-area-action-weight-detail.component.html'
})
export class MAreaActionWeightDetailComponent implements OnInit {
  mAreaActionWeight: IMAreaActionWeight;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAreaActionWeight }) => {
      this.mAreaActionWeight = mAreaActionWeight;
    });
  }

  previousState() {
    window.history.back();
  }
}
