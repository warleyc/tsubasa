import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCommonBanner } from 'app/shared/model/m-common-banner.model';

@Component({
  selector: 'jhi-m-common-banner-detail',
  templateUrl: './m-common-banner-detail.component.html'
})
export class MCommonBannerDetailComponent implements OnInit {
  mCommonBanner: IMCommonBanner;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCommonBanner }) => {
      this.mCommonBanner = mCommonBanner;
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
