import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';

@Component({
  selector: 'jhi-m-gacha-rendition-ball-detail',
  templateUrl: './m-gacha-rendition-ball-detail.component.html'
})
export class MGachaRenditionBallDetailComponent implements OnInit {
  mGachaRenditionBall: IMGachaRenditionBall;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionBall }) => {
      this.mGachaRenditionBall = mGachaRenditionBall;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
