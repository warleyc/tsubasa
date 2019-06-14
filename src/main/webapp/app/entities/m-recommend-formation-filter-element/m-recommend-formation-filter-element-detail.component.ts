import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMRecommendFormationFilterElement } from 'app/shared/model/m-recommend-formation-filter-element.model';

@Component({
  selector: 'jhi-m-recommend-formation-filter-element-detail',
  templateUrl: './m-recommend-formation-filter-element-detail.component.html'
})
export class MRecommendFormationFilterElementDetailComponent implements OnInit {
  mRecommendFormationFilterElement: IMRecommendFormationFilterElement;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mRecommendFormationFilterElement }) => {
      this.mRecommendFormationFilterElement = mRecommendFormationFilterElement;
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
