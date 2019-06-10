import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryEn } from 'app/shared/model/m-dictionary-en.model';

@Component({
  selector: 'jhi-m-dictionary-en-detail',
  templateUrl: './m-dictionary-en-detail.component.html'
})
export class MDictionaryEnDetailComponent implements OnInit {
  mDictionaryEn: IMDictionaryEn;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryEn }) => {
      this.mDictionaryEn = mDictionaryEn;
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
