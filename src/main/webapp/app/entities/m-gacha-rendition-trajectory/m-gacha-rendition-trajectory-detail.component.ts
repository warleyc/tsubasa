import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGachaRenditionTrajectory } from 'app/shared/model/m-gacha-rendition-trajectory.model';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-detail',
  templateUrl: './m-gacha-rendition-trajectory-detail.component.html'
})
export class MGachaRenditionTrajectoryDetailComponent implements OnInit {
  mGachaRenditionTrajectory: IMGachaRenditionTrajectory;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectory }) => {
      this.mGachaRenditionTrajectory = mGachaRenditionTrajectory;
    });
  }

  previousState() {
    window.history.back();
  }
}
