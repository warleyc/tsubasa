import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMConstant } from 'app/shared/model/m-constant.model';

@Component({
  selector: 'jhi-m-constant-detail',
  templateUrl: './m-constant-detail.component.html'
})
export class MConstantDetailComponent implements OnInit {
  mConstant: IMConstant;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mConstant }) => {
      this.mConstant = mConstant;
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
