import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryEs } from 'app/shared/model/m-dictionary-es.model';

@Component({
  selector: 'jhi-m-dictionary-es-detail',
  templateUrl: './m-dictionary-es-detail.component.html'
})
export class MDictionaryEsDetailComponent implements OnInit {
  mDictionaryEs: IMDictionaryEs;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryEs }) => {
      this.mDictionaryEs = mDictionaryEs;
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
