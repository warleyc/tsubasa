import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';

@Component({
  selector: 'jhi-m-model-uniform-up-detail',
  templateUrl: './m-model-uniform-up-detail.component.html'
})
export class MModelUniformUpDetailComponent implements OnInit {
  mModelUniformUp: IMModelUniformUp;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mModelUniformUp }) => {
      this.mModelUniformUp = mModelUniformUp;
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
