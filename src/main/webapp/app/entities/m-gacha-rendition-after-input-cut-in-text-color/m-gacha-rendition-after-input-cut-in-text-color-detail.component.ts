import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-text-color-detail',
  templateUrl: './m-gacha-rendition-after-input-cut-in-text-color-detail.component.html'
})
export class MGachaRenditionAfterInputCutInTextColorDetailComponent implements OnInit {
  mGachaRenditionAfterInputCutInTextColor: IMGachaRenditionAfterInputCutInTextColor;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionAfterInputCutInTextColor }) => {
      this.mGachaRenditionAfterInputCutInTextColor = mGachaRenditionAfterInputCutInTextColor;
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
