import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMMarathonBoostSchedule } from 'app/shared/model/m-marathon-boost-schedule.model';

@Component({
  selector: 'jhi-m-marathon-boost-schedule-detail',
  templateUrl: './m-marathon-boost-schedule-detail.component.html'
})
export class MMarathonBoostScheduleDetailComponent implements OnInit {
  mMarathonBoostSchedule: IMMarathonBoostSchedule;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMarathonBoostSchedule }) => {
      this.mMarathonBoostSchedule = mMarathonBoostSchedule;
    });
  }

  previousState() {
    window.history.back();
  }
}
