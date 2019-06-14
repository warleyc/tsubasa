import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPvpWave } from 'app/shared/model/m-pvp-wave.model';

@Component({
  selector: 'jhi-m-pvp-wave-detail',
  templateUrl: './m-pvp-wave-detail.component.html'
})
export class MPvpWaveDetailComponent implements OnInit {
  mPvpWave: IMPvpWave;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpWave }) => {
      this.mPvpWave = mPvpWave;
    });
  }

  previousState() {
    window.history.back();
  }
}
