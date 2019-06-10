import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDefine } from 'app/shared/model/m-define.model';

@Component({
  selector: 'jhi-m-define-detail',
  templateUrl: './m-define-detail.component.html'
})
export class MDefineDetailComponent implements OnInit {
  mDefine: IMDefine;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDefine }) => {
      this.mDefine = mDefine;
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
