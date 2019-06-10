import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';

@Component({
  selector: 'jhi-m-dictionary-ja-detail',
  templateUrl: './m-dictionary-ja-detail.component.html'
})
export class MDictionaryJaDetailComponent implements OnInit {
  mDictionaryJa: IMDictionaryJa;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryJa }) => {
      this.mDictionaryJa = mDictionaryJa;
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
