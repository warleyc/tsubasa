import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMStamp } from 'app/shared/model/m-stamp.model';

@Component({
  selector: 'jhi-m-stamp-detail',
  templateUrl: './m-stamp-detail.component.html'
})
export class MStampDetailComponent implements OnInit {
  mStamp: IMStamp;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mStamp }) => {
      this.mStamp = mStamp;
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
