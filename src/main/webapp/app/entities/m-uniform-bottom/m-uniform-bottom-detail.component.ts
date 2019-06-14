import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMUniformBottom } from 'app/shared/model/m-uniform-bottom.model';

@Component({
  selector: 'jhi-m-uniform-bottom-detail',
  templateUrl: './m-uniform-bottom-detail.component.html'
})
export class MUniformBottomDetailComponent implements OnInit {
  mUniformBottom: IMUniformBottom;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUniformBottom }) => {
      this.mUniformBottom = mUniformBottom;
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
