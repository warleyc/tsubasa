import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';

@Component({
  selector: 'jhi-m-card-thumbnail-assets-detail',
  templateUrl: './m-card-thumbnail-assets-detail.component.html'
})
export class MCardThumbnailAssetsDetailComponent implements OnInit {
  mCardThumbnailAssets: IMCardThumbnailAssets;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardThumbnailAssets }) => {
      this.mCardThumbnailAssets = mCardThumbnailAssets;
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
