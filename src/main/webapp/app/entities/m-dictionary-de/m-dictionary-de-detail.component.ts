import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryDe } from 'app/shared/model/m-dictionary-de.model';

@Component({
  selector: 'jhi-m-dictionary-de-detail',
  templateUrl: './m-dictionary-de-detail.component.html'
})
export class MDictionaryDeDetailComponent implements OnInit {
  mDictionaryDe: IMDictionaryDe;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryDe }) => {
      this.mDictionaryDe = mDictionaryDe;
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
