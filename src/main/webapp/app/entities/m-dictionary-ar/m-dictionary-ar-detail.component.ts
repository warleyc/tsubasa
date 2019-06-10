import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryAr } from 'app/shared/model/m-dictionary-ar.model';

@Component({
  selector: 'jhi-m-dictionary-ar-detail',
  templateUrl: './m-dictionary-ar-detail.component.html'
})
export class MDictionaryArDetailComponent implements OnInit {
  mDictionaryAr: IMDictionaryAr;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryAr }) => {
      this.mDictionaryAr = mDictionaryAr;
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
