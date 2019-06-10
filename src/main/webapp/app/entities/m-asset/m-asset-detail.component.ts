import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAsset } from 'app/shared/model/m-asset.model';

@Component({
  selector: 'jhi-m-asset-detail',
  templateUrl: './m-asset-detail.component.html'
})
export class MAssetDetailComponent implements OnInit {
  mAsset: IMAsset;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAsset }) => {
      this.mAsset = mAsset;
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
