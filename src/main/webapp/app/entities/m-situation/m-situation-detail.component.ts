import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMSituation } from 'app/shared/model/m-situation.model';

@Component({
  selector: 'jhi-m-situation-detail',
  templateUrl: './m-situation-detail.component.html'
})
export class MSituationDetailComponent implements OnInit {
  mSituation: IMSituation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mSituation }) => {
      this.mSituation = mSituation;
    });
  }

  previousState() {
    window.history.back();
  }
}
