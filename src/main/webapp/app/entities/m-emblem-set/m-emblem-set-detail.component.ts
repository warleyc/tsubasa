import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMEmblemSet } from 'app/shared/model/m-emblem-set.model';

@Component({
  selector: 'jhi-m-emblem-set-detail',
  templateUrl: './m-emblem-set-detail.component.html'
})
export class MEmblemSetDetailComponent implements OnInit {
  mEmblemSet: IMEmblemSet;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mEmblemSet }) => {
      this.mEmblemSet = mEmblemSet;
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
