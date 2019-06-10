import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';

@Component({
  selector: 'jhi-m-dictionary-pt-detail',
  templateUrl: './m-dictionary-pt-detail.component.html'
})
export class MDictionaryPtDetailComponent implements OnInit {
  mDictionaryPt: IMDictionaryPt;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryPt }) => {
      this.mDictionaryPt = mDictionaryPt;
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
