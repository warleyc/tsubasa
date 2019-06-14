import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMStoreReviewUrl } from 'app/shared/model/m-store-review-url.model';

@Component({
  selector: 'jhi-m-store-review-url-detail',
  templateUrl: './m-store-review-url-detail.component.html'
})
export class MStoreReviewUrlDetailComponent implements OnInit {
  mStoreReviewUrl: IMStoreReviewUrl;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStoreReviewUrl }) => {
      this.mStoreReviewUrl = mStoreReviewUrl;
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
