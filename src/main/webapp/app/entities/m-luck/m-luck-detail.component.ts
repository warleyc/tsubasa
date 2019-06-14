import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMLuck } from 'app/shared/model/m-luck.model';

@Component({
  selector: 'jhi-m-luck-detail',
  templateUrl: './m-luck-detail.component.html'
})
export class MLuckDetailComponent implements OnInit {
  mLuck: IMLuck;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mLuck }) => {
      this.mLuck = mLuck;
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
