import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';

@Component({
  selector: 'jhi-m-guild-top-banner-detail',
  templateUrl: './m-guild-top-banner-detail.component.html'
})
export class MGuildTopBannerDetailComponent implements OnInit {
  mGuildTopBanner: IMGuildTopBanner;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mGuildTopBanner }) => {
      this.mGuildTopBanner = mGuildTopBanner;
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
