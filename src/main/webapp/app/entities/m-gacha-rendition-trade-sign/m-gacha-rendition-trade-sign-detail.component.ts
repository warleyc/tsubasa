import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';

@Component({
  selector: 'jhi-m-gacha-rendition-trade-sign-detail',
  templateUrl: './m-gacha-rendition-trade-sign-detail.component.html'
})
export class MGachaRenditionTradeSignDetailComponent implements OnInit {
  mGachaRenditionTradeSign: IMGachaRenditionTradeSign;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionTradeSign }) => {
      this.mGachaRenditionTradeSign = mGachaRenditionTradeSign;
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
