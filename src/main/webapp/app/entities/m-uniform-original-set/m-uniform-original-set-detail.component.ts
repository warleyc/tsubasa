import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';

@Component({
  selector: 'jhi-m-uniform-original-set-detail',
  templateUrl: './m-uniform-original-set-detail.component.html'
})
export class MUniformOriginalSetDetailComponent implements OnInit {
  mUniformOriginalSet: IMUniformOriginalSet;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUniformOriginalSet }) => {
      this.mUniformOriginalSet = mUniformOriginalSet;
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
