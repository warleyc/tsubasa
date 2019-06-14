import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';

@Component({
  selector: 'jhi-m-tsubasa-point-detail',
  templateUrl: './m-tsubasa-point-detail.component.html'
})
export class MTsubasaPointDetailComponent implements OnInit {
  mTsubasaPoint: IMTsubasaPoint;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mTsubasaPoint }) => {
      this.mTsubasaPoint = mTsubasaPoint;
    });
  }

  previousState() {
    window.history.back();
  }
}
