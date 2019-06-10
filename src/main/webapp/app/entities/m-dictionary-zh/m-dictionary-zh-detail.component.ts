import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';

@Component({
  selector: 'jhi-m-dictionary-zh-detail',
  templateUrl: './m-dictionary-zh-detail.component.html'
})
export class MDictionaryZhDetailComponent implements OnInit {
  mDictionaryZh: IMDictionaryZh;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryZh }) => {
      this.mDictionaryZh = mDictionaryZh;
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
