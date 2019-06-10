import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';

@Component({
  selector: 'jhi-m-asset-tag-mapping-detail',
  templateUrl: './m-asset-tag-mapping-detail.component.html'
})
export class MAssetTagMappingDetailComponent implements OnInit {
  mAssetTagMapping: IMAssetTagMapping;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mAssetTagMapping }) => {
      this.mAssetTagMapping = mAssetTagMapping;
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
