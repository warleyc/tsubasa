import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMUniformUp } from 'app/shared/model/m-uniform-up.model';

@Component({
  selector: 'jhi-m-uniform-up-detail',
  templateUrl: './m-uniform-up-detail.component.html'
})
export class MUniformUpDetailComponent implements OnInit {
  mUniformUp: IMUniformUp;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mUniformUp }) => {
      this.mUniformUp = mUniformUp;
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
