import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGachaRenditionTrajectoryPhoenix } from 'app/shared/model/m-gacha-rendition-trajectory-phoenix.model';

@Component({
  selector: 'jhi-m-gacha-rendition-trajectory-phoenix-detail',
  templateUrl: './m-gacha-rendition-trajectory-phoenix-detail.component.html'
})
export class MGachaRenditionTrajectoryPhoenixDetailComponent implements OnInit {
  mGachaRenditionTrajectoryPhoenix: IMGachaRenditionTrajectoryPhoenix;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTrajectoryPhoenix }) => {
      this.mGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenix;
    });
  }

  previousState() {
    window.history.back();
  }
}
