import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';

@Component({
  selector: 'jhi-m-card-illust-assets-detail',
  templateUrl: './m-card-illust-assets-detail.component.html'
})
export class MCardIllustAssetsDetailComponent implements OnInit {
  mCardIllustAssets: IMCardIllustAssets;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardIllustAssets }) => {
      this.mCardIllustAssets = mCardIllustAssets;
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
