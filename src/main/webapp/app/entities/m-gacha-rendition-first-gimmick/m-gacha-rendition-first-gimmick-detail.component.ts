import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMGachaRenditionFirstGimmick } from 'app/shared/model/m-gacha-rendition-first-gimmick.model';

@Component({
  selector: 'jhi-m-gacha-rendition-first-gimmick-detail',
  templateUrl: './m-gacha-rendition-first-gimmick-detail.component.html'
})
export class MGachaRenditionFirstGimmickDetailComponent implements OnInit {
  mGachaRenditionFirstGimmick: IMGachaRenditionFirstGimmick;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionFirstGimmick }) => {
      this.mGachaRenditionFirstGimmick = mGachaRenditionFirstGimmick;
    });
  }

  previousState() {
    window.history.back();
  }
}
