import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';

@Component({
  selector: 'jhi-m-luck-rate-group-detail',
  templateUrl: './m-luck-rate-group-detail.component.html'
})
export class MLuckRateGroupDetailComponent implements OnInit {
  mLuckRateGroup: IMLuckRateGroup;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuckRateGroup }) => {
      this.mLuckRateGroup = mLuckRateGroup;
    });
  }

  previousState() {
    window.history.back();
  }
}
