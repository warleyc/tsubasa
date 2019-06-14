import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';

@Component({
  selector: 'jhi-m-pvp-regulation-detail',
  templateUrl: './m-pvp-regulation-detail.component.html'
})
export class MPvpRegulationDetailComponent implements OnInit {
  mPvpRegulation: IMPvpRegulation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpRegulation }) => {
      this.mPvpRegulation = mPvpRegulation;
    });
  }

  previousState() {
    window.history.back();
  }
}
