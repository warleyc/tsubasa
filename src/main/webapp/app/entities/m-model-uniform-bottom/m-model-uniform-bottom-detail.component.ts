import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';

@Component({
  selector: 'jhi-m-model-uniform-bottom-detail',
  templateUrl: './m-model-uniform-bottom-detail.component.html'
})
export class MModelUniformBottomDetailComponent implements OnInit {
  mModelUniformBottom: IMModelUniformBottom;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformBottom }) => {
      this.mModelUniformBottom = mModelUniformBottom;
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
