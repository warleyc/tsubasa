import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMMovieAsset } from 'app/shared/model/m-movie-asset.model';

@Component({
  selector: 'jhi-m-movie-asset-detail',
  templateUrl: './m-movie-asset-detail.component.html'
})
export class MMovieAssetDetailComponent implements OnInit {
  mMovieAsset: IMMovieAsset;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mMovieAsset }) => {
      this.mMovieAsset = mMovieAsset;
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
