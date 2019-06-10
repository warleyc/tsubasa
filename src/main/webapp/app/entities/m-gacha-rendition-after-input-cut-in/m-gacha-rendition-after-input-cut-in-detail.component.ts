import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';

@Component({
  selector: 'jhi-m-gacha-rendition-after-input-cut-in-detail',
  templateUrl: './m-gacha-rendition-after-input-cut-in-detail.component.html'
})
export class MGachaRenditionAfterInputCutInDetailComponent implements OnInit {
  mGachaRenditionAfterInputCutIn: IMGachaRenditionAfterInputCutIn;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGachaRenditionAfterInputCutIn }) => {
      this.mGachaRenditionAfterInputCutIn = mGachaRenditionAfterInputCutIn;
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
