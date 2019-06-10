import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';

@Component({
  selector: 'jhi-m-card-playable-assets-detail',
  templateUrl: './m-card-playable-assets-detail.component.html'
})
export class MCardPlayableAssetsDetailComponent implements OnInit {
  mCardPlayableAssets: IMCardPlayableAssets;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mCardPlayableAssets }) => {
      this.mCardPlayableAssets = mCardPlayableAssets;
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
