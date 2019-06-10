import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMDictionaryIt } from 'app/shared/model/m-dictionary-it.model';

@Component({
  selector: 'jhi-m-dictionary-it-detail',
  templateUrl: './m-dictionary-it-detail.component.html'
})
export class MDictionaryItDetailComponent implements OnInit {
  mDictionaryIt: IMDictionaryIt;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mDictionaryIt }) => {
      this.mDictionaryIt = mDictionaryIt;
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
