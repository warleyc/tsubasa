import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPvpPlayerStamp } from 'app/shared/model/m-pvp-player-stamp.model';

@Component({
  selector: 'jhi-m-pvp-player-stamp-detail',
  templateUrl: './m-pvp-player-stamp-detail.component.html'
})
export class MPvpPlayerStampDetailComponent implements OnInit {
  mPvpPlayerStamp: IMPvpPlayerStamp;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpPlayerStamp }) => {
      this.mPvpPlayerStamp = mPvpPlayerStamp;
    });
  }

  previousState() {
    window.history.back();
  }
}
