import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMRecommendFormationFilter } from 'app/shared/model/m-recommend-formation-filter.model';

@Component({
  selector: 'jhi-m-recommend-formation-filter-detail',
  templateUrl: './m-recommend-formation-filter-detail.component.html'
})
export class MRecommendFormationFilterDetailComponent implements OnInit {
  mRecommendFormationFilter: IMRecommendFormationFilter;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRecommendFormationFilter }) => {
      this.mRecommendFormationFilter = mRecommendFormationFilter;
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
