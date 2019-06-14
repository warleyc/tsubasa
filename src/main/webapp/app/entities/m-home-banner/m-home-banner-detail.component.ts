import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMHomeBanner } from 'app/shared/model/m-home-banner.model';

@Component({
  selector: 'jhi-m-home-banner-detail',
  templateUrl: './m-home-banner-detail.component.html'
})
export class MHomeBannerDetailComponent implements OnInit {
  mHomeBanner: IMHomeBanner;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mHomeBanner }) => {
      this.mHomeBanner = mHomeBanner;
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
