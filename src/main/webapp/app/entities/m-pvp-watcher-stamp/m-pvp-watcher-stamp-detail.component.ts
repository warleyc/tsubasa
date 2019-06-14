import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';

@Component({
  selector: 'jhi-m-pvp-watcher-stamp-detail',
  templateUrl: './m-pvp-watcher-stamp-detail.component.html'
})
export class MPvpWatcherStampDetailComponent implements OnInit {
  mPvpWatcherStamp: IMPvpWatcherStamp;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mPvpWatcherStamp }) => {
      this.mPvpWatcherStamp = mPvpWatcherStamp;
    });
  }

  previousState() {
    window.history.back();
  }
}
