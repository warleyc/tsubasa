import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMFullPowerPoint } from 'app/shared/model/m-full-power-point.model';

@Component({
  selector: 'jhi-m-full-power-point-detail',
  templateUrl: './m-full-power-point-detail.component.html'
})
export class MFullPowerPointDetailComponent implements OnInit {
  mFullPowerPoint: IMFullPowerPoint;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mFullPowerPoint }) => {
      this.mFullPowerPoint = mFullPowerPoint;
    });
  }

  previousState() {
    window.history.back();
  }
}
