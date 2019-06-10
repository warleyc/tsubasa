import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';

@Component({
  selector: 'jhi-m-distribute-param-point-detail',
  templateUrl: './m-distribute-param-point-detail.component.html'
})
export class MDistributeParamPointDetailComponent implements OnInit {
  mDistributeParamPoint: IMDistributeParamPoint;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDistributeParamPoint }) => {
      this.mDistributeParamPoint = mDistributeParamPoint;
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
