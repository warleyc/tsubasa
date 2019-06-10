import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMCoopRoomStamp } from 'app/shared/model/m-coop-room-stamp.model';

@Component({
  selector: 'jhi-m-coop-room-stamp-detail',
  templateUrl: './m-coop-room-stamp-detail.component.html'
})
export class MCoopRoomStampDetailComponent implements OnInit {
  mCoopRoomStamp: IMCoopRoomStamp;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCoopRoomStamp }) => {
      this.mCoopRoomStamp = mCoopRoomStamp;
    });
  }

  previousState() {
    window.history.back();
  }
}
